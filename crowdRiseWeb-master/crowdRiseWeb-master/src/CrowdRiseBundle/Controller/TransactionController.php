<?php

namespace CrowdRiseBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use CrowdRiseBundle\Entity\Transaction;
use \Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpKernel\Exception\HttpException;
use Beelab\PaypalBundle\Paypal\Exception;

class TransactionController extends Controller {

    public function paymentAction(Request $request) {
        
        $amount = $request->request->get('amount');  // get an amount, e.g. from your cart
        $transaction = new Transaction($amount);
        try {
            $response = $this->get('beelab_paypal.service')->setTransaction($transaction)->start();
            $this->getDoctrine()->getManager()->persist($transaction);
            $this->getDoctrine()->getManager()->flush();

            return $this->redirect($response->getRedirectUrl());
        } catch (Exception $e) {
            throw new HttpException(503, 'Payment error', $e);
        }
    }

    public function cancelAction(Request $request) {
        $token = $request->query->get('token');
        $transaction = $this->getDoctrine()->getRepository('CrowdRiseBundle:Transaction')->findOneByToken($token);
        if (is_null($transaction)) {
            throw $this->createNotFoundException(sprintf('Transaction with token %s not found.', $token));
        }
        $transaction->cancel(null);
        $this->getDoctrine()->getManager()->flush();

        return array(); // or a Response...
    }

    public function completedAction(Request $request) {
        $token = $request->query->get('token');
        $transaction = $this->getDoctrine()->getRepository('CrowdRiseBundle:Transaction')->findOneByToken($token);
        $user = $this->getDoctrine()->getRepository('CrowdRiseBundle:Membre')->find($this->getUser()->getId());
        if (is_null($transaction)) {
            throw $this->createNotFoundException(sprintf('Transaction with token %s not found.', $token));
        }
        $user->setCr($user->getCr()+$transaction->getAmount()* 10);
        $this->get('beelab_paypal.service')->setTransaction($transaction)->complete();
        $this->getDoctrine()->getManager()->flush();
        if (!$transaction->isOk()) {
            return render("Error"); // or a Response (in case of error)
        }
        
        return $this->redirectToRoute('my_profile'); // or a Response (in case of success)
    }

}

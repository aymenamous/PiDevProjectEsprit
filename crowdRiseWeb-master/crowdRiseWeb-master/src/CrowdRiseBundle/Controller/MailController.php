<?php
namespace CrowdRiseBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class MailController extends Controller{

    public function sendAction() {
        $req = $this->get("request");
        if ($req->getMethod() == "POST") {
            $subject = $req->get('subject');
            $mail = $req->get('mail');
            $messageText = $req->get('message');
            
            $message = \Swift_Message::newInstance()
                ->setSubject($subject)
                ->setFrom('testfirasbarrek@gmail.com')
                ->setTo('testfirasbarrek@gmail.com')
                ->setCharset('utf-8')
                ->setContentType('text/html')
                ->setBody($messageText);
        
        $this->get('mailer')->send($message);
        
        return $this->render('CrowdRiseBundle:mail:formulaireMail.html.twig');
        }
        return $this->render('CrowdRiseBundle:mail:formulaireMail.html.twig');
    }

}

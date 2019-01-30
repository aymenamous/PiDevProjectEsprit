<?php

namespace CrowdRiseBundle\Controller;

use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\RedirectResponse;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
class LoginController {
/**
     * @Route("/login", name="login")
     * @Method({"GET"})
     */
    public function loginAction(Request $request)
    {
        if ($this->getUser())
        {
            // already-logged user accessed /login
            return $this->redirect($request->headers->get('referer'));
        }
        else
        {
            // redirect to the login page
            return $this->forward('HWIOAuthBundle:Connect:connect');
        }
    }

    /**
     * @Route("/logout", name="logout")
     * @Method({"GET"})
     */
    public function logoutAction(Request $request)
    {
        // we do a manual logout just to redirect the user to where he comes from
        $this->container->get('security.context')->setToken(null);
        return $this->redirect($request->headers->get('referer'));
    }

    /**
     * @Route("/connect/{service}", name="connect")
     * @Method({"GET"})
     */
    public function connectAction(Request $request, $service)
    {
        // we overwrite this route to store user's referer in the session
        $this->get('session')->set('referer', $request->headers->get('referer'));
        return $this->forward('HWIOAuthBundle:Connect:redirectToService', array('service' => $service));
    }

    /**
     * @Route("/welcome", name="welcome")
     * @Method({"GET"})
     */
    public function welcomeAction()
    {
        // on login success, we're redirected to this route...
        // time to use the referer we previously stored.
        $referer = $this->get('session')->get('referer');
        if (is_null($referer))
        {
            return new RedirectResponse($this->generateUrl('crowd_rise_homepage'));
        }
        return new RedirectResponse($referer);
    }

}
    
    
    
    

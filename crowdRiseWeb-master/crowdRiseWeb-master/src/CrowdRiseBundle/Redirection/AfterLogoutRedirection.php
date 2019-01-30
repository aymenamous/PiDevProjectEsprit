<?php

namespace CrowdRiseBundle\Redirection;
use Symfony\Component\HttpFoundation\RedirectResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\RouterInterface;
use Symfony\Component\Security\Core\SecurityContextInterface;
use Symfony\Component\Security\Http\Logout\LogoutSuccessHandlerInterface;

class AfterLogoutRedirection implements LogoutSuccessHandlerInterface{
    /**
     * @var \Symfony\Component\Routing\RouterInterface
     */
    private $router;
   
    /**
     * @var \Symfony\Component\Security\Core\SecurityContextInterface
     */
    private $security;
    /**
     * @param SecurityContextInterface $security
     */
    public function __construct(RouterInterface $router, SecurityContextInterface $security)
    {
        $this->router = $router;
        $this->security = $security;
     
    }
    /**
     * @param Request $request
     * @return RedirectResponse
     */
    public function onLogoutSuccess(Request $request)
    {
        // Get list of roles for current user
      
        // Tranform this list in array
        
            $response = new RedirectResponse($this->router->generate('crowd_rise_homepage'));
        return $response;
    }
}

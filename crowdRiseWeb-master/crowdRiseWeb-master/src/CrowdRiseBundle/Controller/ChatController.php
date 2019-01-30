<?php

namespace CrowdRiseBundle\Controller;

use CrowdRiseBundle\Entity;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
class ChatController extends Controller{
    public function afficheAction() {
            return $this->render('CrowdRiseBundle:Chat:chat.html.twig');
        
    }
}

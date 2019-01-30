<?php

namespace CrowdRiseBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use \CrowdRiseBundle\Entity\Options;
use CrowdRiseBundle\Form\OptionsType;
class OptionsController extends Controller
{
    public function addAction()
        {
            $om = $this->getDoctrine()->getManager();
            $idee = $om->getRepository("CrowdRiseBundle:Projet")->findBy(array('statut' => 0, 'type' => 'Idee'));
            $probleme = $om->getRepository("CrowdRiseBundle:Probleme")->findBy(array('etat' => 0));
            $projetcf = $om->getRepository("CrowdRiseBundle:Projet")->findBy(array('statut' => 0, 'type' => 'ProjetCF'));
            $membre = $om->getRepository("CrowdRiseBundle:Membre")->findBy(array('statut' => 0));
            $idee_all = $om->getRepository("CrowdRiseBundle:Projet")->findBy(array('statut' => 1, 'type' => 'Idee'));
            
        $model = new Options('','','');
        $form = new OptionsType();
        $f = $this->createForm($form, $model);
        $request = $this->get("request");
        if ($f->handleRequest($request)->isValid()) {
            echo " done";
            $em = $this->getDoctrine()->getManager();
            $em->persist($model);
            $em->flush();
            unset($f);
            unset($model);
            $model = new Options('','','');
            $f = $this->createForm($form, $model);
        }
        
        return $this->render('CrowdRiseBundle:Options:add.html.twig', array('f' => $f->createView(),'idee' => $idee, 'probleme' => $probleme,
                        'projetcf' => $projetcf, 'membre' => $membre, 'idee_all' => $idee_all));      
    }
  
            
            

    public function displayAction()
    {
        return $this->render('CrowdRiseBundle:Options:display.html.twig', array(
                // ...
            ));    }

}

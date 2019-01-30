<?php

namespace CrowdRiseBundle\Controller;
use CrowdRiseBundle\Entity\Survey;
use CrowdRiseBundle\Form\SurveyType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class SurveyController extends Controller
{
    public function addAction()
    {
        $om = $this->getDoctrine()->getManager();
            $idee = $om->getRepository("CrowdRiseBundle:Projet")->findBy(array('statut' => 0, 'type' => 'Idee'));
            $probleme = $om->getRepository("CrowdRiseBundle:Probleme")->findBy(array('etat' => 0));
            $projetcf = $om->getRepository("CrowdRiseBundle:Projet")->findBy(array('statut' => 0, 'type' => 'ProjetCF'));
            $membre = $om->getRepository("CrowdRiseBundle:Membre")->findBy(array('statut' => 0));
            $idee_all = $om->getRepository("CrowdRiseBundle:Projet")->findBy(array('statut' => 1, 'type' => 'Idee'));
            
        $model = new Survey('');
        $form = new SurveyType();
        $f = $this->createForm($form, $model);
        $request = $this->get("request");
        if ($f->handleRequest($request)->isValid()) {
            echo " done";
            $em = $this->getDoctrine()->getManager();
            $em->persist($model);
            $em->flush();
            return $this->redirectToRoute('addoptions');
        }
        
        return $this->render('CrowdRiseBundle:Survey:add.html.twig', array('f' => $f->createView(),'idee' => $idee, 'probleme' => $probleme,
                        'projetcf' => $projetcf, 'membre' => $membre, 'idee_all' => $idee_all));      
    }

    public function displayAction()
    {
        $options = [];
        $om = $this->getDoctrine()->getManager();
        
        $model = $om->getRepository("CrowdRiseBundle:Options")->findAll();
        
        foreach ($model as $mod)
        {
            $opt = $om->getRepository('CrowdRiseBundle:Survey')->find($mod->getIdsurvey());
            array_push($options,$opt);
        }
        return $this->render('CrowdRiseBundle:Survey:display.html.twig', array('options' => $model,'tab'=>$options));
    }
}
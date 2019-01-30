<?php

namespace CrowdRiseBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use CrowdRiseBundle\Form\NomCompetenceType;
use CrowdRiseBundle\Entity\NomCompetence;

class NomCompetenceController extends Controller {
    
    public function addAction() {
        $comp = new NomCompetence();
        $form = new NomCompetenceType();
        $f = $this->createForm($form, $comp,array(
            'action' => $this->generateUrl('crowdrise_competence_add')
        ));
        $request = $this->get('Request');
        if ($f->handleRequest($request)->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($comp);
            $em->flush();
            return $this->redirectToRoute('crowdrise_competence_display');
        }
        return $this->redirectToRoute('crowdrise_competence_display');
    }

    public function displayAction() {
        $om = $this->getDoctrine()->getManager();
        $idee = $om->getRepository("CrowdRiseBundle:Projet")->findBy(array('statut' => 0, 'type' => 'Idee'));
            $probleme = $om->getRepository("CrowdRiseBundle:Probleme")->findBy(array('etat' => 0));
            $projetcf = $om->getRepository("CrowdRiseBundle:Projet")->findBy(array('statut' => 0, 'type' => 'ProjetCF'));
            $membre = $om->getRepository("CrowdRiseBundle:Membre")->findBy(array('statut' => 0));
            $idee_all = $om->getRepository("CrowdRiseBundle:Projet")->findBy(array('statut' => 1, 'type' => 'Idee'));
        
        $comp = new NomCompetence();
        $form = new NomCompetenceType();
        $f = $this->createForm($form, $comp,array(
            'action' => $this->generateUrl('crowdrise_competence_add')
        ));
        $comps = $this->getDoctrine()->getRepository('CrowdRiseBundle:NomCompetence')->findAll();

        return $this->render('CrowdRiseBundle:NomCompetence:display.html.twig', array(
                    'comps' => $comps,
                    'form' => $f->createView(),
            'user' => $this->getUser(),'idee' => $idee, 'probleme' => $probleme,
                        'projetcf' => $projetcf, 'membre' => $membre, 'idee_all' => $idee_all
        ));
    }

    public function deleteAction($id) {

        $em = $this->getDoctrine()->getManager();
        $comp = $em->getRepository("CrowdRiseBundle:NomCompetence")->find($id);
        $em->remove($comp);
        $em->flush();

        return $this->redirectToRoute('crowdrise_competence_display');
    }
    
    public function updateAction($id){
        $em = $this->getDoctrine()->getManager();
        $comp = $em->getRepository("CrowdRiseBundle:NomCompetence")->find($id);
        $request = $this->get('Request');
        $name = $request->request->get('name','');
        if($name){
            $comp->setNom($name);
        }
        $em->flush();
        return $this->redirectToRoute('crowdrise_competence_display');
        
    }
}

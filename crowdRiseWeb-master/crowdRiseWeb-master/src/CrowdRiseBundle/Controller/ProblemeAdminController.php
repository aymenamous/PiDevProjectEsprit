<?php

namespace CrowdRiseBundle\Controller;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class ProblemeAdminController extends Controller {
    
    public function displayAllAction() {
        $om = $this->getDoctrine()->getManager();
        $idee = $om->getRepository("CrowdRiseBundle:Projet")->findBy(array('statut' => 0, 'type' => 'Idee'));
        $probleme = $om->getRepository("CrowdRiseBundle:Probleme")->findBy(array('etat' => 0));
        $projetcf = $om->getRepository("CrowdRiseBundle:Projet")->findBy(array('statut' => 0, 'type' => 'ProjetCF'));
        $membre = $om->getRepository("CrowdRiseBundle:Membre")->findBy(array('statut' => 0));
        $idee_all = $om->getRepository("CrowdRiseBundle:Projet")->findBy(array('statut' => 1, 'type' => 'Idee'));
        return $this->render('CrowdRiseBundle:Probleme:listProblemeAdmin.html.twig', array('idee' => $idee, 'probleme' => $probleme,
                    'projetcf' => $projetcf, 'membre' => $membre, 'idee_all' => $idee_all));
    }
        
    public function AccepterAction($id){
        
        $em=$this->getDoctrine()->getManager();
        $modele=$em->getRepository('CrowdRiseBundle:Probleme')->find($id);
        $modele->SetEtat(1);
        $em->persist($modele);
        $em->flush();
        return $this->redirectToRoute('crowdrise_probleme_Admin');
    }
    
    public function RefuserAction($id){
        
        $em=$this->getDoctrine()->getManager();
        $modele=$em->getRepository('CrowdRiseBundle:Probleme')->find($id);
        $em->remove($modele);
        $em->flush();
        return $this->redirectToRoute('crowdrise_probleme_Admin');
    }
}

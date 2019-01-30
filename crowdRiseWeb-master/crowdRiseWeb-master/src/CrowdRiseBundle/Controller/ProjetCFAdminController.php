<?php

namespace CrowdRiseBundle\Controller;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class ProjetCFAdminController extends Controller {
    
   public function displayAllAction() {
        $om = $this->getDoctrine()->getManager();
        $idee = $om->getRepository("CrowdRiseBundle:Projet")->findBy(array('statut' => 0, 'type' => 'Idee'));
        $probleme = $om->getRepository("CrowdRiseBundle:Probleme")->findBy(array('etat' => 0));
        $projetcf = $om->getRepository("CrowdRiseBundle:Projet")->findBy(array('statut' => 0, 'type' => 'ProjetCF'));
        $membre = $om->getRepository("CrowdRiseBundle:Membre")->findBy(array('statut' => 0));
        $signalisation = $om->getRepository("CrowdRiseBundle:Signalisation")->findAll();


        return $this->render('CrowdRiseBundle:ProjetCf:listProjetCfAdmin.html.twig', array('idee' => $idee,'probleme'=>$probleme,
          'projetcf'=>$projetcf,'membre'=>$membre,'signalisation'=>$signalisation));
    }
    
     public function AccepterAction($id){
        
        $em=$this->getDoctrine()->getManager();
        $modele=$em->getRepository('CrowdRiseBundle:Projet')->find($id);
        $modele->SetStatut(1);
        $em->persist($modele);
        $em->flush();
        return $this->redirectToRoute('crowdrise_projetcf_Admin');
    }
    
    public function RefuserAction($id){
        
        $em=$this->getDoctrine()->getManager();
        $modele=$em->getRepository('CrowdRiseBundle:Projet')->find($id);
        $em->remove($modele);
        $em->flush();
        return $this->redirectToRoute('crowdrise_projetcf_Admin');
    }
    
    //Affichage de tous les projets validé du coté de l'admin, avec possibilité de suppression (made by Aymen)
    public function displayAllAdminAction()
    {
        $om = $this->getDoctrine()->getManager();
        $idee = $om->getRepository("CrowdRiseBundle:Projet")->findBy(array('statut' => 0, 'type' => 'Idee'));
        $probleme = $om->getRepository("CrowdRiseBundle:Probleme")->findBy(array('etat' => 0));
        $projetcf = $om->getRepository("CrowdRiseBundle:Projet")->findBy(array('statut' => 1, 'type' => 'ProjetCF'));
        $membre = $om->getRepository("CrowdRiseBundle:Membre")->findBy(array('statut' => 0));
        $idee_all = $om->getRepository("CrowdRiseBundle:Projet")->findBy(array('statut' => 1, 'type' => 'Idee'));
        return $this->render('CrowdRiseBundle:ProjetCf:listAllProjetCfAdmin.html.twig', array('projetcf' => $projetcf,'idee' => $idee,'probleme'=>$probleme,
            'membre'=>$membre,'idee_all'=>$idee_all));
    }
    
    public function deleteAction($id){
        
        $em=$this->getDoctrine()->getManager();
        $modele=$em->getRepository('CrowdRiseBundle:Projet')->find($id);
        $em->remove($modele);
        $em->flush();
        return $this->redirectToRoute('crowdrise_all_projetcf_admin');
    }
}

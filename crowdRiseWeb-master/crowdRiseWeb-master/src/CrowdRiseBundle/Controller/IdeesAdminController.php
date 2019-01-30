<?php

namespace CrowdRiseBundle\Controller;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class IdeesAdminController extends Controller{
    
   public function displayAllAction() 
        {
            $om = $this->getDoctrine()->getManager();
            $idee = $om->getRepository("CrowdRiseBundle:Projet")->findBy(array('statut' => 0, 'type' => 'Idee'));
            $probleme = $om->getRepository("CrowdRiseBundle:Probleme")->findBy(array('etat' => 0));
            $projetcf = $om->getRepository("CrowdRiseBundle:Projet")->findBy(array('statut' => 0, 'type' => 'ProjetCF'));
            $membre = $om->getRepository("CrowdRiseBundle:Membre")->findBy(array('statut' => 0));
            $idee_all = $om->getRepository("CrowdRiseBundle:Projet")->findBy(array('statut' => 1, 'type' => 'Idee'));
            return $this->render('CrowdRiseBundle:Idees:listIdeeAdmin.html.twig', array('idee' => $idee,'probleme'=>$probleme,
          'projetcf'=>$projetcf,'membre'=>$membre,'idee_all'=>$idee_all));
        }
        
        
   public function AccepterAction($id){
        
        $em=$this->getDoctrine()->getManager();
        $modele=$em->getRepository('CrowdRiseBundle:Projet')->find($id);
        $name=$modele->getNom();
        $modele->SetStatut(1);
        $em->persist($modele);
        $em->flush();
        
        //Ici le mail de validation
        $message = \Swift_Message::newInstance()
                ->setSubject('Validation de votre idée')
                ->setFrom(array('testfirasbarrek@gmail.com' => "Equipe CrowdRise"))
                ->setTo('testfirasbarrek@gmail.com')
                ->setCharset('utf-8')
                ->setContentType('text/html')
                ->setBody($this->renderView('CrowdRiseBundle:Idees:validation.html.twig',array('user' => $this->getUser(),'name'=>$name)));
        
        $this->get('mailer')->send($message);
        
        return $this->redirectToRoute('crowdrise_idees_Admin');
    }
    
    public function RefuserAction($id){
        
        $em=$this->getDoctrine()->getManager();
        $modele=$em->getRepository('CrowdRiseBundle:Projet')->find($id);
        $em->remove($modele);
        $em->flush();
        return $this->redirectToRoute('crowdrise_idees_Admin');
    }
    
    public function deleteAction($id){
        
        $em=$this->getDoctrine()->getManager();
        $modele=$em->getRepository('CrowdRiseBundle:Projet')->find($id);
        $em->remove($modele);
        $em->flush();
        return $this->redirectToRoute('crowdrise_idees_Admin');
    }
}

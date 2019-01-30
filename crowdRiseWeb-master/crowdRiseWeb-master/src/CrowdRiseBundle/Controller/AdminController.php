<?php

namespace CrowdRiseBundle\Controller;

use CrowdRiseBundle\Entity;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class AdminController extends Controller {

    public function indexAction() {

        if ($this->get('security.context')->isGranted('ROLE_SUPER_ADMIN')) {
            $om = $this->getDoctrine()->getManager();
            $idee = $om->getRepository("CrowdRiseBundle:Projet")->findBy(array('statut' => 0, 'type' => 'Idee'));
            $probleme = $om->getRepository("CrowdRiseBundle:Probleme")->findBy(array('etat' => 0));
            $projetcf = $om->getRepository("CrowdRiseBundle:Projet")->findBy(array('statut' => 0, 'type' => 'ProjetCF'));
            $membre = $om->getRepository("CrowdRiseBundle:Membre")->findBy(array('statut' => 0));
            $idee_all = $om->getRepository("CrowdRiseBundle:Projet")->findBy(array('statut' => 1, 'type' => 'Idee'));
            return $this->render('CrowdRiseBundle:Admin:indexAdmin.html.twig', array('idee' => $idee, 'probleme' => $probleme,
                        'projetcf' => $projetcf, 'membre' => $membre, 'idee_all' => $idee_all));
        } else
            return $this->redirectToRoute('crowd_rise_homepage');
    }

}

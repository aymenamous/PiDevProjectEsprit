<?php

namespace CrowdRiseBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\Serializer\Encoder\XmlEncoder;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Normalizer\GetSetMethodNormalizer;
use Symfony\Component\HttpFoundation\JsonResponse;

/**
 * Description of MembreAdmin
 *
 * @author Yosra
 */
class MembreAdminController extends Controller {

    public function displayAllAction() {
        if ($this->get('security.context')->isGranted('ROLE_SUPER_ADMIN')) {

            $om = $this->getDoctrine()->getManager();
            $idee = $om->getRepository("CrowdRiseBundle:Projet")->findBy(array('statut' => 0, 'type' => 'Idee'));
            $probleme = $om->getRepository("CrowdRiseBundle:Probleme")->findBy(array('etat' => 0));
            $projetcf = $om->getRepository("CrowdRiseBundle:Projet")->findBy(array('statut' => 0, 'type' => 'ProjetCF'));
            $membre = $om->getRepository("CrowdRiseBundle:Membre")->findBy(array('statut' => 0));
            $signalisation = $om->getRepository("CrowdRiseBundle:Signalisation")->findAll();
            return $this->render('CrowdRiseBundle:Membre:listMembreAdmin.html.twig', array('idee' => $idee, 'probleme' => $probleme,
                        'projetcf' => $projetcf, 'membre' => $membre, 'signalisation' => $signalisation));
        } else
            return $this->redirectToRoute('crowd_rise_homepage');
    }

    public function AccepterAction($id) {

        $em = $this->getDoctrine()->getManager();
        $modele = $em->getRepository('CrowdRiseBundle:Membre')->find($id);
        $modele->setStatut(1);
        $em->persist($modele);
        $em->flush();
        return $this->redirectToRoute('crowdrise_membre_Admin');
    }

    public function RefuserAction($id) {

        $em = $this->getDoctrine()->getManager();
        $modele = $em->getRepository('CrowdRiseBundle:Membre')->find($id);
        $em->remove($modele);
        $em->flush();
        return $this->redirectToRoute('crowdrise_membre_Admin');
    }

    public function utf8ize($d) {
        if (is_array($d)) {
            foreach ($d as $k => $v) {
                $d[$k] = utf8ize($v);
            }
        } else if (is_string($d)) {
            return utf8_encode($d);
        }
        return $d;
    }

    public function RechercherAction() {
        $request = $this->container->get('request');
        $string = $request->request->get('searchText');
        $action = $request->request->get('action');
        if($action === '1'){
            $users = $this->getDoctrine()->getEntityManager()->createQuery('SELECT u.id, u.nom ,u.prenom,u.email FROM CrowdRiseBundle:Membre u  
                WHERE u.nom LIKE :string OR u.prenom LIKE :string')
                ->setParameter('string', '%' . $string . '%')
                ->getArrayResult();
        }else{
            $users = $this->getDoctrine()->getEntityManager()->createQuery('SELECT u.id, u.nom ,u.prenom,u.email FROM CrowdRiseBundle:Membre u ')
                ->getArrayResult();
        }
        

        //return users on json format
        $encoders = array(new XmlEncoder(), new JsonEncoder());
        $normalizers = array(new GetSetMethodNormalizer());
        $serializer = new Serializer($normalizers, $encoders);
//
        $jsonContent = $serializer->serialize($users, 'json');
//       var_dump($users);
        $response = new Response(json_encode($users));
       $response->headers->set('Content-Type', 'application/json');
       return $response;
//        return $this->render('CrowdRiseBundle::test.html.twig',array('tmp' => $jsonContent));
    }

    public function enableUserAction($id) {
        $em = $this->getDoctrine()->getManager();
        $user = $em->getRepository('CrowdRiseBundle:Membre')->findOneById($id);
        $user->setEnabled(true);
        $em->persist($user);
        $em->flush();

        return $this->redirectToRoute('crowdrise_membre_Admin');
    }

    public function disableUserAction($id) {
        $em = $this->getDoctrine()->getManager();
        $user = $em->getRepository('CrowdRiseBundle:Membre')->findOneById($id);
        $user->setEnabled(false);
        $em->persist($user);
        $em->flush();
        return $this->redirectToRoute('crowdrise_membre_Admin');
    }
    

}

<?php

namespace CrowdRiseBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use \Symfony\Component\HttpFoundation\Request;
use CrowdRiseBundle\Entity\NomCompetence;
use Symfony\Component\HttpFoundation\Response;

class MemberProfileController extends Controller {

    public function myProfileAction() {
        $membre = $this->getUser();
        if($membre->getImg()){
            $membre->setImg(stream_get_contents($membre->getImg()));
        }
        
        $comps = $this->getDoctrine()->getRepository('CrowdRiseBundle:Competence')->findByidMembre($membre->getId());
        $em = $this->getDoctrine()->getManager();
        $query = $em->createQuery(
                        'SELECT p
                        FROM CrowdRiseBundle:Projet p
                        WHERE p.idMembre = :mem_id
                        ORDER BY p.date DESC'
                )->setParameter('mem_id', 2);
        $projs = $query->getResult();
        
        $sol = $this->getDoctrine()->getRepository('CrowdRiseBundle:Solution')->findAll();  

        return $this->render('CrowdRiseBundle:MemberProfile:myProfile.html.twig', array(
                 'sol'   => $sol,
                    'user'  => $this->getUser(),   
            'comps' => $comps,
                    'projs' => $projs,
                    'ncomp' => $this->getDoctrine()->getRepository('CrowdRiseBundle:NomCompetence')->findAll()
        ));
        //return new Response(var_dump($membre));
    }

    public function memberProfileAction($id) {
        return $this->render('CrowdRiseBundle:MemberProfile:memberProfile.html.twig', array(
                        // ...
        ));
    }

    public function editProfileAction() {
        if ($this->getUser() == null) {
            return $this->redirectToRoute('crowd_rise_homepage');
        } else {
            $em = $this->getDoctrine()->getManager();
            $object = $em->getRepository(\CrowdRiseBundle\Entity\Membre::class)->find($this->getUser()->getId());
            $form = new \CrowdRiseBundle\Form\MembreType();
            $f = $this->createForm($form, $object);
            $request = $this->get("request");
            if ($f->handleRequest($request)->isValid()) {
                $em->flush();
            }
            return $this->render('CrowdRiseBundle:MemberProfile:editProfile.html.twig', array('f' => $f->createView(), 'user' => $this->getUser()));
        }
    }

    public function addSkillAction(Request $request) {

        $em = $this->getDoctrine()->getManager();
        $user = $this->getUser();

        if ($request->getMethod() === "POST") {
            $i = 0;

            while ($request->request->get('comp' . $i)) {

                $comp = new \CrowdRiseBundle\Entity\Competence();
                $comp->setIdMembre($user);
                $comp->setNom($request->request->get('comp' . $i));
                
                $em->persist($comp);
                $i++;
            }

            $em->flush();
        }
        return $this->redirectToRoute('my_profile');
    }

    public function picChangeAction(Request $request) {
        $em = $this->getDoctrine()->getManager();
        $membre = $em->getRepository('CrowdRiseBundle:Membre')->find($this->getUser()->getId());
        $img = $request->request->get('src', '');
        $membre->setImg($img);
        $em->flush();

        return $this->redirectToRoute('my_profile');
    }

}

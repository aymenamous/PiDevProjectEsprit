<?php

namespace CrowdRiseBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Response;
use CrowdRiseBundle\Entity\Probleme;

/**
 * Description of ProblemeController
 *
 * @author H4DH
 */
class ProblemeController extends Controller {

    public function displayAllAction() 
    {
        $om = $this->getDoctrine()->getManager();
        $model = $om->getRepository("CrowdRiseBundle:Probleme")->findBy(array('etat' => 1));
        if ($this->getUser() == NULL) {
            return $this->render('CrowdRiseBundle:Probleme:listProbleme.html.twig', array('route' => '{{route}}', 'tab' => $model));
        } else {
            $model1 = $om->getRepository("CrowdRiseBundle:Probleme")->findBy(array('etat' => 1, 'idMembre' => $this->getUser()->getId()));
            return $this->render('CrowdRiseBundle:Probleme:listProbleme.html.twig', array('route' => '{{route}}', 'tab' => $model, 'tab1' => $model1));
        }
    }

    public function addAction() {
        $model = new Probleme();
        $form = new \CrowdRiseBundle\Form\ProblemeType();
        $f = $this->createForm($form, $model);
        $request = $this->get("request");
        if ($f->handleRequest($request)->isValid()) {
            echo " done";
            $model->setEtat(0);
            $model->setIdMembre($this->getUser());
            $model->setDate(new \DateTime('today'));
            $em = $this->getDoctrine()->getManager();
            $em->persist($model);
            $em->flush();
        }
        return $this->render("CrowdRiseBundle:Probleme:ajoutProbleme.html.twig", array('f' => $f->createView()));
    }

    public function deleteAction($id) {
        $em = $this->getDoctrine()->getManager();
        $object = $em->getRepository("CrowdRiseBundle:Probleme")->find($id);
        $em->remove($object);
        $em->flush();
        return $this->redirectToRoute("crowdrise_probleme_displayAll");
    }


    
    
    public function updateAction($id) {

            $em = $this->getDoctrine()->getManager();
            $object = $em->getRepository("CrowdRiseBundle:Probleme")->find($id);
            if ($this->getUser() == $object->getIdMembre())
            {
                $form = new \CrowdRiseBundle\Form\ProblemeType();
                $f = $this->createForm($form, $object);
                $request = $this->get("request");
                if ($f->handleRequest($request)->isValid()) {
                    $em->flush();
                    return $this->redirectToRoute("crowdrise_probleme_displayAll");
                }
                return $this->render("CrowdRiseBundle:Probleme:ajoutProbleme.html.twig", array('f' => $f->createView(),'update'=>'0'));
            }
            return $this->redirectToRoute("crowdrise_probleme_displayAll");
        }
    }



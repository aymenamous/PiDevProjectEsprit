<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace CrowdRiseBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Response;
use CrowdRiseBundle\Entity\Projet;
use Symfony\Component\HttpFoundation\Request;
use CrowdRiseBundle\Entity\NomCompetence;
use CrowdRiseBundle\Entity\Competence;
use CrowdRiseBundle\Entity\Solution;

/**
 * Description of ProjetCfController
 *
 * @author Triki
 */
class IdeeController extends Controller {

    public function displayAllAction(Request $request) {
        $em = $this->get('doctrine.orm.entity_manager');
        $dql = "SELECT a FROM CrowdRiseBundle:Projet a where a.statut='1' and a.type='Idee'";
        $query = $em->createQuery($dql);
        $paginator = $this->get('knp_paginator');
        $pagination = $paginator->paginate(
                $query, $request->query->getInt('page', 1), 8
        );
        $pagination->setTemplate('CrowdRiseBundle:Idee:paginate.html.twig');
        return $this->render('CrowdRiseBundle:Idee:index.html.twig', array('user' => $this->getUser(), 'route' => '{{route}}', 'tab' => $pagination));
    }

    public function SearchAction() {
        $model2 = new Competence();
        $content = $this->get("request")->getContent();
        $params = json_decode($content, true);
        $em = $this->getDoctrine()->getEntityManager();
        $query = $em->createQuery("SELECT l.nom"
                        . "  FROM CrowdRiseBundle:Projet l"
                        . " where l.type='idee'"
                        . "and l.nom like :prjet"
                )->setParameter('prjet', "%" . $params['info'] . "%");
        $result = $query->getArrayResult();

        $response = new Response(json_encode($result));
        $response->headers->set('Content-Type', 'application/json');
        return $response;
    }

    public function AddMembreTacheAction() {
        $request = $this->container->get('request');
        $Sol = new Solution();
        if ($request->isXmlHttpRequest()) {
            $em = $this->getDoctrine()->getManager();
            $Sol = $em->getRepository('CrowdRiseBundle:Solution')->find($request->request->get('idSolution'));
            $Sol->setIdMembre($this->getUser());
            if (!$Sol) {
                throw $this->createNotFoundException(
                        'No product found for id ' . $id
                );
            }
            $em->flush();
            $response = new Response(json_encode(array('Status' => 200)));
            $response->headers->set('Content-Type', 'application/json');
            return $response;
        }
    }

    public function displayTachesAction() {
        $model2 = new Competence();
        $request = $this->container->get('request');
        if ($request->isXmlHttpRequest()) {

            $em = $this->getDoctrine()->getEntityManager();
            $query = $em->createQuery('SELECT l,r.nom,IDENTITY(l.idMembre) as id'
                            . '  FROM CrowdRiseBundle:Solution l left join CrowdRiseBundle:Membre m'
                            . ' with l.idMembre=m.id ,'
                            . ' CrowdRiseBundle:NomCompetence r'
                            . ' where'
                            . ' l.nomCompetence=r.id '
                            . 'and l.idProjet = :prjet'
                    )->setParameter('prjet', $request->request->get('idprj'));
            $result = $query->getArrayResult();

            $query1 = $em->createQuery('SELECT l FROM CrowdRiseBundle:Competence l where l.idMembre = :idmbr')->setParameter('idmbr', $this->getUser()->getId());
            $result1 = $query1->getArrayResult();

            $response = new Response(json_encode(array('resSol' => $result, 'resCompt' => $result1, 'Status' => 200)));
            $response->headers->set('Content-Type', 'application/json');
            return $response;
        }
    }

    public function AddTachesAction() {
        $model1 = new Projet();
        $model2 = new NomCompetence();
        $request = $this->container->get('request');
        $form = new \CrowdRiseBundle\Form\IdeeType();
        $f = $this->createForm($form, $model1);
        if ($request->isXmlHttpRequest()) {
            foreach ($request->request->get('info') as $value) {
                if ($value[1] != "Cliquez sur playlist_add pour ajouter une nouvelle tache") {
                    $model = new Solution();
                    $model->setConfirmOwner(False);
                    $model->setConfirmSolver(False);
                    //$model->setIdMembre($this->getUser());
                    $om = $this->getDoctrine()->getManager();
                    $prjt = $om->getRepository("CrowdRiseBundle:Projet")->findBy(array('nom' => $request->request->get('nom')));
                    $model->setIdProjet($prjt[0]);
                    $model->setRemuneration($value[1]);
                    $model->setTache($value[0]);
                    $model2->setNom($value[2]);
                    $idCompt = $om->getRepository("CrowdRiseBundle:NomCompetence")->findBy(array('nom' => $value[2]));
                    $model->setNomCompetence($idCompt[0]);
                    $em = $this->container->get('doctrine')->getManager();
                    $em->persist($model);
                }
            }
            $em->flush();
            $html = $this->container->get('templating')->renderResponse('CrowdRiseBundle:Idee:ajoutIdee.html.twig', array(
                'f' => $f->createView()
            ));
            $response = new Response(json_encode(array("html" => $html, 'Status' => 200)));
            $response->headers->set('Content-Type', 'application/json');
            return $response;
        } else {
            return $this->render("CrowdRiseBundle:Idee:ajoutIdee.html.twig", array('tab' => $compt, 'f' => $f->createView()));
        }
    }

    public function displayMembresAction() {
        return'';
    }

    public function addAction() {
        $om = $this->getDoctrine()->getManager();
        $em = $this->getDoctrine()->getEntityManager();
        $query = $em->createQuery('SELECT l FROM CrowdRiseBundle:NomCompetence l');
        $result = $query->getArrayResult();
        $compt = $om->getRepository("CrowdRiseBundle:NomCompetence")->findAll();
        $model = new Projet();
        $model->setType("idee");
        $model->setDate(new \DateTime('today'));
        $model->setStatut(0);
        $form = new \CrowdRiseBundle\Form\IdeeType();
        $f = $this->createForm($form, $model);
        $request = $this->container->get('request');
        if ($request->isXmlHttpRequest()) {
            $files = $request->files;
            foreach ($files as $file) {
                $model->setImg($file);
                $model->uploadPicture();
            }
            $model->setImageidee($request->request->get('nomdesfichiers'));
            $model->setNom($request->request->get('1'));
            $model->setDebut(new \DateTime($request->request->get('2')));
            $model->setFin(new \DateTime($request->request->get('3')));
            $model->setDescription($request->request->get('4'));
            $model->setRemunerationTotale($request->request->get('5'));
            $model->setIdMembre($this->getUser());
            $em = $this->container->get('doctrine')->getManager();
            $em->persist($model);
            $em->flush();

            $response = new Response(json_encode($result));
            $response->headers->set('Content-Type', 'application/json');
            return $response;
        } else {

            return $this->render("CrowdRiseBundle:Idee:ajoutIdee.html.twig", array('tab' => $compt, 'f' => $f->createView()));
        }
        return $this->render("CrowdRiseBundle:Idee:ajoutIdee.html.twig", array('f' => $f->createView()));
    }

    public function deleteAction($id) {
        $request = $this->container->get('request');
        if ($request->isXmlHttpRequest()) {
            $em = $this->getDoctrine()->getManager();
            $object = $em->getRepository("CrowdRiseBundle:Projet")->find($id);
            $em->remove($object);
            $em->flush();
            $html = $this->container->get('templating')->renderResponse('CrowdRiseBundle:idee:portfolio-showcaseMesIdees.html.twig');
            $response = new Response(json_encode(array("html" => $html, 'Status' => $id)));
            $response->headers->set('Content-Type', 'application/json');
            return $response;
        }
    }

    public function updateAction($id) {
        if ($this->getUser() == null) {
            return $this->redirectToRoute('crowd_rise_homepage');
        } else {
            $em = $this->getDoctrine()->getManager();
            $object = $em->getRepository("CrowdRiseBundle:Projet")->find($id);
            if ($this->getUser() == $object->getIdMembre())
            {
                $form = new \CrowdRiseBundle\Form\IdeeType();
                $f = $this->createForm($form, $object);
                $request = $this->get("request");
                if ($f->handleRequest($request)->isValid()) {
                   
                    $em->flush();
                    return $this->redirectToRoute("crowdrise_idee_displayAll");
                }
                return $this->render("CrowdRiseBundle:Idee:updateidee.html.twig", array('f' => $f->createView(),'update'=>'0'));
            }
            return $this->redirectToRoute("crowdrise_idee_displayAll");
        }
    }

}

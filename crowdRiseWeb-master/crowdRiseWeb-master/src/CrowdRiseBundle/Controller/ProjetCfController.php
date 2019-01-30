<?php

namespace CrowdRiseBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Response;
use CrowdRiseBundle\Entity\Projet;
use Ob\HighchartsBundle\Highcharts\Highchart;
use Zend\Json\Expr;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of ProjetCfController
 *
 * @author Aymen
 */
class ProjetCfController extends Controller {

    //put your code here

    public function displayAllAction() {
        $om = $this->getDoctrine()->getManager();
        $model = $om->getRepository("CrowdRiseBundle:Projet")->findBy(array('statut' => 1, 'type' => 'ProjetCF'));
        $finance = $om->getRepository(\CrowdRiseBundle\Entity\Financement::class)->findAll();
        return $this->render('CrowdRiseBundle:ProjetCf:listProjetCf.html.twig', array('tab' => $model, 'user' => $this->getUser(),'contribute'=>$finance));
    }

    public function addAction() {
        $model = new Projet();
        $form = new \CrowdRiseBundle\Form\ProjetCfType();
        $f = $this->createForm($form, $model);
        $request = $this->get("request");
        if ($f->handleRequest($request)->isValid()) {
            $model->setIdMembre($this->getUser());
            $model->setType("ProjetCF");
            $model->setBudgetActuel(0);
            $em = $this->getDoctrine()->getManager();
            $em->persist($model);
            $em->flush();
            return $this->redirectToRoute('crowdrise_projetcf_displayAll');
        }
        if ($this->getUser() == null) {
            return $this->redirectToRoute('crowd_rise_homepage');
        } else {
            return $this->render("CrowdRiseBundle:ProjetCf:ajoutProjetCf.html.twig", array('f' => $f->createView()));
        }
    }

    public function deleteAction($id) {
        if ($this->getUser() == null) {
            return $this->redirectToRoute('crowd_rise_homepage');
        } else {
            $em = $this->getDoctrine()->getManager();
            $object =$em->getRepository("CrowdRiseBundle:Projet")->find($id);
            
            if ($this->getUser() == $object->getIdMembre())
            {
                $em->remove($object);
                $em->flush();
            }
            return $this->redirectToRoute("crowdrise_projetcf_displayAll");
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
                $form = new \CrowdRiseBundle\Form\ProjetCfUpdateType();
                $f = $this->createForm($form, $object);
                $request = $this->get("request");
                if ($f->handleRequest($request)->isValid()) {
                    $em->flush();
                    return $this->redirectToRoute("crowdrise_projetcf_displayAll");
                }
                return $this->render("CrowdRiseBundle:ProjetCf:ajoutProjetCf.html.twig", array('f' => $f->createView(),'update'=>'0'));
            }
            return $this->redirectToRoute("crowdrise_projetcf_displayAll");
        }
    }

    public function searchByNameAction() {
        
    }
    
    public function updateImgAction($id,$img)
    {
        if ($this->getUser() != null)
        {
            $em = $this->getDoctrine()->getManager();
            $projet = $em->getRepository(Projet::class)->find($id);
            if ($this->getUser() == $projet->getIdMembre())
            {
                $projet->setImageidee($img);
                $em->flush();
            }
        }
        return $this->redirectToRoute('crowdrise_projetcf_displayAll');
    }
    public function chartLineAction($id){

        // Chart
         if ($this->getUser() == null) {
            return $this->redirectToRoute('crowd_rise_homepage');
        } else {
        $sql = " SELECT date,count(*) nbr,SUM(somme) somme  FROM financement where id_projet = $id  group by date ";
        $em = $this->getDoctrine()->getManager();
        $stmt = $em->getConnection()->prepare($sql);
        $stmt->execute();
        $result=$stmt->fetchAll();
        $projet = $em->getRepository(Projet::class)->find($id);
        if ($this->getUser() == $projet->getIdMembre())
            {
        foreach ($result as $value) {
            $xAxis[]=$value['date'];
            $data[]=(int)$value['nbr'];
            $data1[]=(int)$value['somme'];
        }
        $series = array(array("name" => "Nombre", "data" => $data));
        $series1=array(array("name" => "Financement", "data" => $data1));

        $ob = new Highchart();
        $ob->chart->renderTo('nombre'); // #id du div où afficher le graphe
        $ob->title->text('Graphique du nombre de contributions au projet '.$projet->getNom().' en fonction de la date');
        $ob->xAxis->title(array('text' => "Date"));
        $ob->yAxis->title(array('text' => "Nombre de contribution "));
        $ob->xAxis->categories($xAxis);
        $ob->series($series);
        
        $ob1 = new Highchart();
        $ob1->chart->renderTo('somme'); // #id du div où afficher le graphe
        $ob1->title->text('Graphique de la somme des financements du projet '.$projet->getNom().' en fonction de la date');
        $ob1->xAxis->title(array('text' => "Date"));
        $ob1->yAxis->title(array('text' => "Somme des financements (en Cr) "));
        $ob1->xAxis->categories($xAxis);
        $ob1->series($series1);

        return $this->render('CrowdRiseBundle:ProjetCf:statsFinancement.html.twig', array(

        'chart' => $ob,'chart1'=>$ob1

        ));

    }}}

}

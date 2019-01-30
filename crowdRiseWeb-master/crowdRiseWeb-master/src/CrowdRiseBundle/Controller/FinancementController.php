<?php

namespace CrowdRiseBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class FinancementController extends Controller
{
    function addAction()
    {
        $request = $this->get("request");
        if ($request->getMethod()=="POST")
        {
            $em = $this->getDoctrine()->getManager();
            $somme = $request->get('modal-somme-cf');
            $id = $request->get("modal-id-cf");
            $projet = $em->getRepository(\CrowdRiseBundle\Entity\Projet::class)->find($id);
            $membre = $this->getUser();
            $financement = new \CrowdRiseBundle\Entity\Financement($somme, $projet, $membre);
            $em->persist($financement);
            $actuel = $projet->getBudgetActuel()+$somme;
            $projet->setBudgetActuel($actuel);
            $membre->setCr($membre->getCr()-$somme);
            $em->flush();
        }
        return $this->redirectToRoute('crowdrise_projetcf_displayAll');
    }
}

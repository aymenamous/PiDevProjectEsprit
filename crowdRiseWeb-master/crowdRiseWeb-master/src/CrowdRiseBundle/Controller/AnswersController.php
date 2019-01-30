<?php

namespace CrowdRiseBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use CrowdRiseBundle\Entity\Answers;
use Symfony\Component\HttpFoundation\Request;
class AnswersController extends Controller
{
    public function addAction(Request $request)
    {
       $tmp=$request->request->get('group');
       $em= $this->getDoctrine()->getManager();
       $id=$request->request->get('id');
       $user = $this->getUser();
       
       for($i=0;$i<sizeof($tmp);$i++)
       {
           $val = $tmp[$i];
           $methode = "setOption".$val;
           $answer= new Answers ('0','0','0');
           $answer->$methode('1');
           $answer->setIdmembre($user);
           $opt=$em->getRepository('CrowdRiseBundle:Options')->find($id[$i]);
           $answer->setIdoptions($opt);
           $em->persist($answer); 
       }
       $em->flush();
       return $this->redirectToRoute('addanswer1');   
    }
     
    public function displayAction()
    {

        $em= $this->getDoctrine()->getManager();
        $query = $em->createQuery('SELECT sum(a.option1) as option1, sum(a.option2) as option2, sum(a.option3) as option3,
      s.question, s.id, o.option1 as soption1, o.option2 as soption2, o.option3 as soption3 FROM CrowdRiseBundle:Answers a join a.idoptions o join o.idsurvey s  
      WHERE a.option1 = 1 or a.option2 = 1 or a.option3 = 1 group by s.id ');
        $results = $query->getResult();

      return $this->render('CrowdRiseBundle:Answers:display.html.twig',array('tmp'=>$results));  
    }

}

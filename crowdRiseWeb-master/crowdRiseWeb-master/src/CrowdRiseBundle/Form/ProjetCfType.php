<?php

namespace CrowdRiseBundle\Form;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolverInterface;



class ProjetCfType extends AbstractType
{
    /**
     * @param FormBuilderInterface $builder
     * @param array $options
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('nom')
            
            ->add('fin','date',array('widget' => 'single_text','attr' => array('class'=>'projetCfDate')))
            ->add('description','textarea')
            ->add('budgetFinale')
            ->add('imageidee','file',array('data_class'=>null))
        ;
    }
    
    /**
     * @param OptionsResolverInterface $resolver
     */
    public function setDefaultOptions(OptionsResolverInterface $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'CrowdRiseBundle\Entity\Projet'
        ));
    }

    /**
     * @return string
     */
    public function getName()
    {
        return 'crowdrisebundle_projet';
    }
}

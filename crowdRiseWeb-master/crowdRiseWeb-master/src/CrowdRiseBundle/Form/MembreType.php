<?php

namespace CrowdRiseBundle\Form;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolverInterface;
use Gregwar\CaptchaBundle\Type\CaptchaType;

class MembreType extends AbstractType
{
    /**
     * @param FormBuilderInterface $builder
     * @param array $options
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('nom')
            ->add('prenom')
            ->add('adresse')
            ->add('telephone')
            ->add('password','password',array('required'=>false))
            ->add('captcha', 'captcha')
        ;
    }
    
    /**
     * @param OptionsResolverInterface $resolver
     */
    public function setDefaultOptions(OptionsResolverInterface $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'CrowdRiseBundle\Entity\Membre'
        ));
    }

    /**
     * @return string
     */
    public function getName()
    {
        return 'crowdrisebundle_membre';
    }
}

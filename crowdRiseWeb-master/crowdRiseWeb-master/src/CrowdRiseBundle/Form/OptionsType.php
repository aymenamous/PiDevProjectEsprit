<?php

namespace CrowdRiseBundle\Form;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolverInterface;

class OptionsType extends AbstractType
{
    /**
     * @param FormBuilderInterface $builder
     * @param array $options
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('idsurvey')
            ->add('option1')
            ->add('option2')
            ->add('option3')

        ;
    }
    
    /**
     * @param OptionsResolverInterface $resolver
     */
    public function setDefaultOptions(OptionsResolverInterface $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'CrowdRiseBundle\Entity\Options'
        ));
    }

    /**
     * @return string
     */
    public function getName()
    {
        return 'crowdrisebundle_options';
    }
}

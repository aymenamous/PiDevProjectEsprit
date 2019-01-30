<?php

namespace CrowdRiseBundle\Form;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolverInterface;

class ProblemeType extends AbstractType
{
    /**
     * @param FormBuilderInterface $builder
     * @param array $options
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder

            ->add('description','textarea')
            ->add('titre')
            ->add('imageoption','file', array('data_class' => 'Symfony\Component\HttpFoundation\File\File'))

        ;
    }
    
    /**
     * @param OptionsResolverInterface $resolver
     */
    public function setDefaultOptions(OptionsResolverInterface $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'CrowdRiseBundle\Entity\Probleme'
        ));
    }

    /**
     * @return string
     */
    public function getName()
    {
        return 'crowdrisebundle_probleme';
    }
}

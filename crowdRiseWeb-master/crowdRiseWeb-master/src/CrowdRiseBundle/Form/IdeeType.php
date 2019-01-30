<?php

namespace CrowdRiseBundle\Form;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolverInterface;
use Symfony\Component\Validator\Constraints\Range;

class IdeeType extends AbstractType {

    /**
     * @param FormBuilderInterface $builder
     * @param array $options
     */
    public function buildForm(FormBuilderInterface $builder, array $options) {
        $builder
                ->add('nom')
                ->add('debut', 'date', array('widget' => 'single_text', 'attr' => array('class' => 'datepick')))
                ->add('fin', 'date', array('widget' => 'single_text', 'attr' => array('class' => 'datepick')))
                ->add('description', 'textarea')
                ->add('remuneration_totale', 'integer')
                //->add('img','file',array('required' => false))
                ->add('img', 'file', array(
                    
                    'attr' => array(
                        'accept' => 'image/*',
                        "multiple" => "multiple",
                    ),
                ))
        ;
    }

    /**
     * @param OptionsResolverInterface $resolver
     */
    public function setDefaultOptions(OptionsResolverInterface $resolver) {
        $resolver->setDefaults(array(
            'data_class' => 'CrowdRiseBundle\Entity\Projet'
        ));
    }

    /**
     * @return string
     */
    public function getName() {
        return 'crowdrisebundle_projet';
    }

}

<?php

use Symfony\Component\HttpKernel\Kernel;
use Symfony\Component\Config\Loader\LoaderInterface;

class AppKernel extends Kernel {

    public function registerBundles() {
        $bundles = array(
            new Symfony\Bundle\FrameworkBundle\FrameworkBundle(),
            new Symfony\Bundle\SecurityBundle\SecurityBundle(),
            new Symfony\Bundle\TwigBundle\TwigBundle(),
            new Symfony\Bundle\MonologBundle\MonologBundle(),
            new Symfony\Bundle\SwiftmailerBundle\SwiftmailerBundle(),
            new Symfony\Bundle\AsseticBundle\AsseticBundle(),
            new Doctrine\Bundle\DoctrineBundle\DoctrineBundle(),
            new Sensio\Bundle\FrameworkExtraBundle\SensioFrameworkExtraBundle(),
            new AppBundle\AppBundle(),
            new CrowdRiseBundle\CrowdRiseBundle(),
            new FOS\UserBundle\FOSUserBundle(),

            new FOS\RestBundle\FOSRestBundle(),
            new FOS\CommentBundle\FOSCommentBundle(),
            new JMS\SerializerBundle\JMSSerializerBundle($this),
            new Ob\HighchartsBundle\ObHighchartsBundle(),
            new DCS\RatingBundle\DCSRatingBundle(),
            new Nomaya\SocialBundle\NomayaSocialBundle(),
            new HWI\Bundle\OAuthBundle\HWIOAuthBundle(),

            new FOS\JsRoutingBundle\FOSJsRoutingBundle(),
            new Gregwar\CaptchaBundle\GregwarCaptchaBundle(),
            
            new Cunningsoft\ChatBundle\CunningsoftChatBundle(),
            new Knp\Bundle\TimeBundle\KnpTimeBundle(),
            //new Omnipay\PayPal\ExpressGateway(),
            new Beelab\PaypalBundle\BeelabPaypalBundle(),
            new Knp\Bundle\PaginatorBundle\KnpPaginatorBundle()
        );

        if (in_array($this->getEnvironment(), array('dev', 'test'))) {
            $bundles[] = new Symfony\Bundle\DebugBundle\DebugBundle();
            $bundles[] = new Acme\DemoBundle\AcmeDemoBundle();
            $bundles[] = new Symfony\Bundle\WebProfilerBundle\WebProfilerBundle();
            $bundles[] = new Sensio\Bundle\DistributionBundle\SensioDistributionBundle();
            $bundles[] = new Sensio\Bundle\GeneratorBundle\SensioGeneratorBundle();
        }

        return $bundles;
    }

    public function registerContainerConfiguration(LoaderInterface $loader) {
        $loader->load(__DIR__ . '/config/config_' . $this->getEnvironment() . '.yml');
    }

}

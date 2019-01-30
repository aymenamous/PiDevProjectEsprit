<?php

namespace CrowdRiseBundle\Tests\Controller;

use Symfony\Bundle\FrameworkBundle\Test\WebTestCase;

class CommandeControllerTest extends WebTestCase
{
    public function testDetails()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/payment/detail');
    }

    public function testComplete()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/payment/complete');
    }

}

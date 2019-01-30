<?php

namespace CrowdRiseBundle\Tests\Controller;

use Symfony\Bundle\FrameworkBundle\Test\WebTestCase;

class NomCompetenceControllerTest extends WebTestCase
{
    public function testAdd()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/competence/add');
    }

    public function testDisplay()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/competence/display');
    }

}

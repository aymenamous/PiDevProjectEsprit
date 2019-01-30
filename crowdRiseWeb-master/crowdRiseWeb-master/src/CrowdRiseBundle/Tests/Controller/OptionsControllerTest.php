<?php

namespace CrowdRiseBundle\Tests\Controller;

use Symfony\Bundle\FrameworkBundle\Test\WebTestCase;

class OptionsControllerTest extends WebTestCase
{
    public function testAdd()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/options/add');
    }

    public function testDisplay()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/options/display');
    }

}

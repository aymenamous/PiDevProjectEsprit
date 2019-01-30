<?php

namespace CrowdRiseBundle\Tests\Controller;

use Symfony\Bundle\FrameworkBundle\Test\WebTestCase;

class TransactionControllerTest extends WebTestCase
{
    public function testPayment()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/payment');
    }

    public function testCancel()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/cancel/payment');
    }

    public function testCompleted()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/completed/payment');
    }

}

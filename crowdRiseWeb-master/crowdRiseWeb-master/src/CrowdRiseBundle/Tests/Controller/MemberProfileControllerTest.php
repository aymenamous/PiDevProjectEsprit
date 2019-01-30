<?php

namespace CrowdRiseBundle\Tests\Controller;

use Symfony\Bundle\FrameworkBundle\Test\WebTestCase;

class MemberProfileControllerTest extends WebTestCase
{
    public function testMyprofile()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/myProfile');
    }

    public function testMemberprofile()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/membre/{id}');
    }

}

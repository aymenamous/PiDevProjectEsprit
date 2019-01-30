<?php

namespace CrowdRiseBundle\Entity;
use Beelab\PaypalBundle\Entity\Transaction as BaseTransaction;
use Doctrine\ORM\Mapping as ORM;

/**
 * Transaction
 *
 * @ORM\Table()
 * @ORM\Entity
 */
class Transaction extends BaseTransaction
{
        
    public function getDescription()
    {
        return "Crs";
    }

    public function getItems()
    {
        $x[] = null;
        return $x;
    }

    public function getShippingAmount()
    {
        // here you can return shipping amount. This amount MUST
        return "00.00";
    }
}

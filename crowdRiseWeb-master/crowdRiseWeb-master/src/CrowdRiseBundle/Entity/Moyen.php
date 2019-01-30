<?php

namespace CrowdRiseBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Moyen
 *
 * @ORM\Table(name="moyen")
 * @ORM\Entity
 */
class Moyen
{
    /**
     * @var string
     *
     * @ORM\Column(name="nom", type="string", length=15, nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $nom;


}

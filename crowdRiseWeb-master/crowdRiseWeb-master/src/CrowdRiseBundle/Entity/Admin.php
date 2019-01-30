<?php

namespace CrowdRiseBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Admin
 *
 * @ORM\Table(name="admin", uniqueConstraints={@ORM\UniqueConstraint(name="uq_email", columns={"email"})})
 * @ORM\Entity
 */
class Admin
{
    /**
     * @var integer
     *
     * @ORM\Column(name="id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="nom", type="string", length=15, nullable=false)
     */
    private $nom;

    /**
     * @var string
     *
     * @ORM\Column(name="prenom", type="string", length=15, nullable=false)
     */
    private $prenom;

    /**
     * @var string
     *
     * @ORM\Column(name="mdp", type="string", length=15, nullable=false)
     */
    private $mdp;

    /**
     * @var string
     *
     * @ORM\Column(name="email", type="string", length=30, nullable=false)
     */
    private $email;

    /**
     * @var string
     *
     * @ORM\Column(name="photo", type="string", length=100, nullable=false)
     */
    private $photo;


}

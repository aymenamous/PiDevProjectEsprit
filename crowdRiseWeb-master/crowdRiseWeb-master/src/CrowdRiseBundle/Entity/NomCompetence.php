<?php

namespace CrowdRiseBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * NomCompetence
 *
 * @ORM\Table(name="nom_competence", uniqueConstraints={@ORM\UniqueConstraint(name="nom", columns={"nom"})})
 * @ORM\Entity
 */
class NomCompetence
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
     * @ORM\Column(name="nom", type="string", length=20, nullable=false)
     */
    private $nom;
    
    function __construct($id = 0, $nom = "") {
        $this->id = $id;
        $this->nom = $nom;
    }

        function getId() {
        return $this->id;
    }

    function getNom() {
        return $this->nom;
    }

    function setId($id) {
        $this->id = $id;
    }

    function setNom($nom) {
        $this->nom = $nom;
    }


}

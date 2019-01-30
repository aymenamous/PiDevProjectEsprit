<?php

namespace CrowdRiseBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Competence
 *
 * @ORM\Table(name="competence", indexes={@ORM\Index(name="id_membre", columns={"id_membre"})})
 * @ORM\Entity
 */
class Competence
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
     * @var \Membre
     *
     * @ORM\ManyToOne(targetEntity="Membre")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_membre", referencedColumnName="id")
     * })
     */
    private $idMembre;
    
    function getId() {
        return $this->id;
    }

    function getNom() {
        return $this->nom;
    }

    function getIdMembre() {
        return $this->idMembre;
    }

    function setId($id) {
        $this->id = $id;
    }

    function setNom($nom) {
        $this->nom = $nom;
    }

    function setIdMembre($idMembre) {
        $this->idMembre = $idMembre;
    }



}

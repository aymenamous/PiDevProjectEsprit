<?php

namespace CrowdRiseBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Probleme
 *
 * @ORM\Table(name="probleme", indexes={@ORM\Index(name="id_membre", columns={"id_membre"})})
 * @ORM\Entity
 */
class Probleme 
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
     * @var \DateTime
     *
     * @ORM\Column(name="date", type="date", nullable=true)
     */
    private $date;

    /**
     * @var string
     *
     * @ORM\Column(name="description", type="string", length=100, nullable=false)
     */
    private $description;

    /**
     * @var string
     *
     * @ORM\Column(name="titre", type="string", length=30, nullable=false)
     */
    private $titre;

    /**
     * @var string
     *
     * @ORM\Column(name="etat", type="string", length=12, nullable=false)
     */
    private $etat;

    /**
     * @var string
     *
     * @ORM\Column(name="imageoption", type="string", length=100, nullable=true)
     */
    private $imageoption;
    
    /**
     * @var \Membre
     *
     * @ORM\ManyToOne(targetEntity="Membre")
     * @ORM\JoinColumns({
     * @ORM\JoinColumn(name="id_membre", referencedColumnName="id")
     * })
     */
    private $idMembre;

    function getId() {
        return $this->id;
    }

    function getDate() {
        return $this->date;
    }

    function getDescription() {
        return $this->description;
    }

    function getTitre() {
        return $this->titre;
    }

    function getEtat() {
        return $this->etat;
    }

    function getIdMembre() {
        return $this->idMembre;
    }

    function setId($id) {
        $this->id = $id;
    }

    function setDate(\DateTime $date) {
        $this->date = $date;
    }

    function setDescription($description) {
        $this->description = $description;
    }

    function setTitre($titre) {
        $this->titre = $titre;
    }

    function setEtat($etat) {
        $this->etat = $etat;
    }

    function setIdMembre($idMembre) {
        $this->idMembre = $idMembre;
    }

    function getImageoption() {
        return $this->imageoption;
    }

    function setImageoption($imageoption) {
        $this->imageoption = $imageoption;
    }


}

<?php

namespace CrowdRiseBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Financement
 *
 * @ORM\Table(name="financement", indexes={@ORM\Index(name="id_projet", columns={"id_projet"}), @ORM\Index(name="id_membre_3", columns={"id_membre"}), @ORM\Index(name="moyen", columns={"moyen"})})
 * @ORM\Entity
 */
class Financement
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
     * @ORM\Column(name="date", type="date", nullable=false)
     */
    private $date;

    /**
     * @var float
     *
     * @ORM\Column(name="somme", type="float", precision=10, scale=0, nullable=false)
     */
    private $somme;

    /**
     * @var \Moyen
     *
     * @ORM\ManyToOne(targetEntity="Moyen")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="moyen", referencedColumnName="nom")
     * })
     */
    private $moyen;

    /**
     * @var \Projet
     *
     * @ORM\ManyToOne(targetEntity="Projet")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_projet", referencedColumnName="id")
     * })
     */
    private $idProjet;

    /**
     * @var \Membre
     *
     * @ORM\ManyToOne(targetEntity="Membre")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_membre", referencedColumnName="id")
     * })
     */
    private $idMembre;
    
    function __construct($somme, $idProjet, $idMembre) {
        $this->somme = $somme;
        $this->idProjet = $idProjet;
        $this->idMembre = $idMembre;
        $this->date=new \DateTime();
    }

    
    function getId() {
        return $this->id;
    }

    function getDate() {
        return $this->date;
    }

    function getSomme() {
        return $this->somme;
    }

    function getMoyen() {
        return $this->moyen;
    }

    function getIdProjet() {
        return $this->idProjet;
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

    function setSomme($somme) {
        $this->somme = $somme;
    }

    function setMoyen(\Moyen $moyen) {
        $this->moyen = $moyen;
    }

    function setIdProjet(\Projet $idProjet) {
        $this->idProjet = $idProjet;
    }

    function setIdMembre(\Membre $idMembre) {
        $this->idMembre = $idMembre;
    }





}

<?php

namespace CrowdRiseBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Signalisation
 *
 * @ORM\Table(name="signalisation", indexes={@ORM\Index(name="id_commentaire", columns={"id_commentaire"}), @ORM\Index(name="id_membre", columns={"id_membre"})})
 * @ORM\Entity
 */
class Signalisation
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
     * @ORM\Column(name="raison", type="string", length=100, nullable=false)
     */
    private $raison;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date", type="date", nullable=false)
     */
    private $date;

    /**
     * @var \Commentaire
     *
     * @ORM\ManyToOne(targetEntity="Commentaire")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_commentaire", referencedColumnName="id")
     * })
     */
    private $idCommentaire;

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

    function getRaison() {
        return $this->raison;
    }

    function getDate() {
        return $this->date;
    }

    function getIdCommentaire() {
        return $this->idCommentaire;
    }

    function getIdMembre() {
        return $this->idMembre;
    }

    function setId($id) {
        $this->id = $id;
    }

    function setRaison($raison) {
        $this->raison = $raison;
    }

    function setDate(\DateTime $date) {
        $this->date = $date;
    }

    function setIdCommentaire(\Commentaire $idCommentaire) {
        $this->idCommentaire = $idCommentaire;
    }

    function setIdMembre(\Membre $idMembre) {
        $this->idMembre = $idMembre;
    }



}

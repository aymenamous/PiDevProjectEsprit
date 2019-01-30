<?php

namespace CrowdRiseBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Solution
 *
 * @ORM\Table(name="solution", indexes={@ORM\Index(name="id_projet", columns={"id_projet"}), @ORM\Index(name="id_membre", columns={"id_membre"})})
 * @ORM\Entity
 */
class Solution
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
     * @var float
     *
     * @ORM\Column(name="remuneration", type="float", precision=10, scale=0, nullable=false)
     */
    private $remuneration;

    /**
     * @var string
     *
     * @ORM\Column(name="tache", type="string", length=30, nullable=false)
     */
    private $tache;

    /**
     * @var boolean
     *
     * @ORM\Column(name="confirm_owner", type="boolean", nullable=false)
     */
    private $confirmOwner;

    /**
     * @var boolean
     *
     * @ORM\Column(name="confirm_solver", type="boolean", nullable=false)
     */
    private $confirmSolver;


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

    /**
     * @var \CrowdRiseBundle\Entity\NomCompetence
     *
     * @ORM\ManyToOne(targetEntity="CrowdRiseBundle\Entity\NomCompetence")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="nom_competence", referencedColumnName="id")
     * })
     */
    private $nomCompetence;

    function getId() {
        return $this->id;
    }

    function getRemuneration() {
        return $this->remuneration;
    }

    function getTache() {
        return $this->tache;
    }

    function getConfirmOwner() {
        return $this->confirmOwner;
    }

    function getConfirmSolver() {
        return $this->confirmSolver;
    }

    function getIdProjet() {
        return $this->idProjet;
    }

    function getIdMembre() {
        return $this->idMembre;
    }

    function getNomCompetence() {
        return $this->nomCompetence;
    }

    function setId($id) {
        $this->id = $id;
    }

    function setRemuneration($remuneration) {
        $this->remuneration = $remuneration;
    }

    function setTache($tache) {
        $this->tache = $tache;
    }

    function setConfirmOwner($confirmOwner) {
        $this->confirmOwner = $confirmOwner;
    }

    function setConfirmSolver($confirmSolver) {
        $this->confirmSolver = $confirmSolver;
    }

    function setIdProjet(\CrowdRiseBundle\Entity\Projet $idProjet) {
        $this->idProjet = $idProjet;
    }

    function setIdMembre(\CrowdRiseBundle\Entity\Membre $idMembre) {
        $this->idMembre = $idMembre;
    }

    function setNomCompetence(\CrowdRiseBundle\Entity\NomCompetence $nomCompetence) {
        $this->nomCompetence = $nomCompetence;
    }


}

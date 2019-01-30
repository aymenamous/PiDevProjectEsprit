<?php

namespace CrowdRiseBundle\Entity;
use Cunningsoft\ChatBundle\Entity\AuthorInterface;
use FOS\UserBundle\Model\User as BaseUser;
use Doctrine\ORM\Mapping as ORM;
use Doctrine\Common\Collections\ArrayCollection;

/**
 * Membre
 *
 * @ORM\Table(name="membre")
 * @ORM\Entity(repositoryClass="CrowdRiseBundle\Entity\MembreRepository")
 */
class Membre extends BaseUser implements AuthorInterface{

    /**
     * @var integer
     *
     * @ORM\Column(name="id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    protected $id;

    /**
     * @var string
     *
     * @ORM\Column(name="facebook_id", type="string", nullable=true)
     */
    protected $facebook_id;

    /**
     * @var string
     *
     * @ORM\Column(name="google_id", type="string", nullable=true)
     */
    protected $google_id;

    /**
     * @var string
     *
     * @ORM\Column(name="twitter_id", type="string", nullable=true)
     */
    protected $twitter_id;

    /**
     * @var string
     *
     * @ORM\Column(name="nom", type="string", length=15, nullable=true)
     */
    protected $nom;

    /**
     * @var string
     *
     * @ORM\Column(name="prenom", type="string", length=15, nullable=true)
     */
    protected $prenom;

    /**
     * @var string
     *
     * @ORM\Column(name="adresse", type="string", length=30, nullable=true)
     */
    private $adresse;

    /**
     * @var string
     *
     * @ORM\Column(name="mdp", type="string", length=15, nullable=true)
     */
    private $mdp;

    /**
     * @var string
     *
     * @ORM\Column(name="telephone", type="string", length=8, nullable=true)
     */
    private $telephone;

    /**
     * @var float
     *
     * @ORM\Column(name="nbr_solved", type="float", precision=10, scale=0, nullable=true)
     */
    private $nbrSolved;

    /**
     * @var integer
     *
     * @ORM\Column(name="cr", type="integer", nullable=true)
     */
    private $cr;

    /**
     * @var string
     *
     * @ORM\Column(name="photo", type="string", length=100, nullable=true)
     */
    private $photo;

    /**
     * @var boolean
     *
     * @ORM\Column(name="statut", type="boolean", nullable=true)
     */
    private $statut;

    function getFacebook_id() {
        return $this->facebook_id;
    }

    /**
     * @var string
     *
     * * @ORM\Column(name="img", type="blob", nullable=true)
     */
    private $img;

    function getGoogle_id() {
        return $this->google_id;
    }

    function getTwitter_id() {
        return $this->twitter_id;
    }

    function setFacebook_id($facebook_id) {
        $this->facebook_id = $facebook_id;
    }

    function setGoogle_id($google_id) {
        $this->google_id = $google_id;
    }

    function setTwitter_id($twitter_id) {
        $this->twitter_id = $twitter_id;
    }

    function getId() {
        return $this->id;
    }

    function getNom() {
        return $this->nom;
    }

    function getPrenom() {
        return $this->prenom;
    }

    function getAdresse() {
        return $this->adresse;
    }

    function getMdp() {
        return $this->mdp;
    }

    function getTelephone() {
        return $this->telephone;
    }

    function getNbrSolved() {
        return $this->nbrSolved;
    }

    function getCr() {
        return $this->cr;
    }

    function getPhoto() {
        return $this->photo;
    }

    function getStatut() {
        return $this->statut;
    }

    function setId($id) {
        $this->id = $id;
    }

    function setNom($nom) {
        $this->nom = $nom;
    }

    function setPrenom($prenom) {
        $this->prenom = $prenom;
    }

    function setAdresse($adresse) {
        $this->adresse = $adresse;
    }

    function setMdp($mdp) {
        $this->mdp = $mdp;
    }

    function setTelephone($telephone) {
        $this->telephone = $telephone;
    }

    function setNbrSolved($nbrSolved) {
        $this->nbrSolved = $nbrSolved;
    }

    function setCr($cr) {
        $this->cr = $cr;
    }

    function setPhoto($photo) {
        $this->photo = $photo;
    }

    function setStatut($statut) {
        $this->statut = $statut;
    }

    public function __construct() {
        parent::__construct();
        $this->media = new \Doctrine\Common\Collections\ArrayCollection();
    }
    function getImg() {
        return $this->img;
    }

    function setImg($img) {
        $this->img = $img;
    }
    
    public function __toString() {
        return $this->username;
    }
    
    



}

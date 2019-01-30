<?php

namespace CrowdRiseBundle\Entity;
use FOS\UserBundle\Model\User as BaseUser; 
use Doctrine\ORM\Mapping as ORM;

/** 
 *  
 *  @ORM\Entity  
 *  @ORM\Table(name="user")  

 * */ 
class User extends BaseUser
{
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
     * @ORM\Column(name="username", type="string", length=255, nullable=false)
     */
   
    /**
     * @var string
     *
     * @ORM\Column(name="nom", type="string", length=255, nullable=false)
     */
   protected $nom;

    /**
     * @var string
     *
     * @ORM\Column(name="prenom", type="string", length=255, nullable=false)
     */
    protected $prenom;

    /**
     * @var string
     *
     * @ORM\Column(name="adresse", type="string", length=255, nullable=true)
     */
    protected $adresse;

    /**
     * @var string
     *
     * @ORM\Column(name="telephone", type="string", length=10, nullable=true)
     */
    protected $telephone;

    /**
     * @var float
     *
     * @ORM\Column(name="nbr_solved", type="float", precision=10, scale=0, nullable=true)
     */
    protected $nbrSolved;

    /**
     * @var integer
     *
     * @ORM\Column(name="cr", type="integer", nullable=true)
     */
    protected $cr;

    /**
     * @var string
     *
     * @ORM\Column(name="photo", type="string", length=255, nullable=true)
     */
    protected $photo;

    /**
     * @var integer
     *
     * @ORM\Column(name="statut", type="integer", nullable=true)
     */
    protected $statut;

   public function __construct()  
     {
       parent::__construct();  
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


   
}

<?php

namespace CrowdRiseBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use CrowdRiseBundle\Entity\Membre;
use Symfony\Component\Validator\Constraints as Assert;
/**
 * Projet
 *
 * @ORM\Table(name="projet", indexes={@ORM\Index(name="id_membre", columns={"id_membre"})})
 * @ORM\Entity
 */
class Projet
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
     * @ORM\Column(name="nom", type="string", length=30, nullable=false)
     */
    private $nom;

    /**
     * @Assert\File(
     *     maxSize = "1024k"     
     * )
     */
    protected $img;
    
    /**
     * @var \DateTime
     *
     * @ORM\Column(name="debut", type="date", nullable=true)
     */
    private $debut;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="fin", type="date", nullable=true)
     */
    private $fin;

    /**
     * @var string
     *
     * @ORM\Column(name="type", type="string", length=30, nullable=true)
     */
    private $type;

    /**
     * @var string
     *
     * @ORM\Column(name="description", type="string", length=100, nullable=true)
     */
    private $description;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date", type="date", nullable=true)
     */
    private $date;

    /**
     * @var float     
     * @ORM\Column(name="remuneration_totale", type="float", precision=10, scale=0, nullable=true)
     */
    private $remunerationTotale;

    /**
     * @var float
     *
     * @ORM\Column(name="budget_actuel", type="float", precision=10, scale=0, nullable=true)
     */
    private $budgetActuel;

    /**
     * @var float
     *
     * @ORM\Column(name="budget_finale", type="float", precision=10, scale=0, nullable=true)
     */
    private $budgetFinale;

    /**
     * @var string
     *
     * @ORM\Column(name="imageIdee", type="string", length=100, nullable=true)
     */
    private $imageidee;

    /**
     * @var integer
     *
     * @ORM\Column(name="statut", type="integer", nullable=true)
     */
    private $statut;

    /**
     * @var \Membre
     *
     * @ORM\ManyToOne(targetEntity="Membre")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_membre", referencedColumnName="id")
     * })
     */
    private $idMembre;

    function getImg() {
        return $this->img;
    }

    function setImg($Img) {
        $this->img = $Img;
    }

        
    
    function __construct() {
        $this->date=new \DateTime();
        $this->debut=new \DateTime();
        $this->statut=0;
        $this->imageidee="";
    }
    
    function getId() {
        return $this->id;
    }

    function getNom() {
        return $this->nom;
    }

    function getDebut() {
        return $this->debut;
    }

    function getFin() {
        return $this->fin;
    }

    function getType() {
        return $this->type;
    }

    function getDescription() {
        return $this->description;
    }

    function getDate() {
        return $this->date;
    }

    function getRemunerationTotale() {
        return $this->remunerationTotale;
    }

    function getBudgetActuel() {
        return $this->budgetActuel;
    }

    function getBudgetFinale() {
        return $this->budgetFinale;
    }

    function getImageidee() {
        return $this->imageidee;
    }

    function getStatut() {
        return $this->statut;
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

    function setDebut(\DateTime $debut) {
        $this->debut = $debut;
    }

    function setFin(\DateTime $fin) {
        $this->fin = $fin;
    }

    function setType($type) {
        $this->type = $type;
    }

    function setDescription($description) {
        $this->description = $description;
    }

    function setDate(\DateTime $date) {
        $this->date = $date;
    }

    function setRemunerationTotale($remunerationTotale) {
        $this->remunerationTotale = $remunerationTotale;
    }

    function setBudgetActuel($budgetActuel) {
        $this->budgetActuel = $budgetActuel;
    }

    function setBudgetFinale($budgetFinale) {
        $this->budgetFinale = $budgetFinale;
    }

    function setImageidee($imageidee) {
        $this->imageidee = $imageidee;
    }

    function setStatut($statut) {
        $this->statut = $statut;
    }

    function setIdMembre(Membre $idMembre) {
        $this->idMembre = $idMembre;
    }
    
    public function getWebPath()
    {
        return null === $this->imageidee ? null : $this->getUploadDir().'/'.$this->imageidee;
    }

    protected function getUploadRootDir()
    {
        // le chemin absolu du répertoire dans lequel sauvegarder les photos de profil
        return __DIR__.'/../../../web/'.$this->getUploadDir();
    }

    protected function getUploadDir()
    {
        // get rid of the __DIR__ so it doesn't screw when displaying uploaded doc/image in the view.
        return 'uploads/pictures';
    }
    
    public function uploadPicture()
    {
        // Nous utilisons le nom de fichier original, donc il est dans la pratique 
        // nécessaire de le nettoyer pour éviter les problèmes de sécurité

        // move copie le fichier présent chez le client dans le répertoire indiqué.
        $this->img->move($this->getUploadRootDir(), $this->img->getClientOriginalName());

        // On sauvegarde le nom de fichier
        $this->imageidee = $this->img->getClientOriginalName();
        
        // La propriété file ne servira plus
        $this->img = null;
    }
    

}

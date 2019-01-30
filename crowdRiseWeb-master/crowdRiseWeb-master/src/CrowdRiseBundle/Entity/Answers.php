<?php

namespace CrowdRiseBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * 
 *
 * @ORM\Table(name="answers", indexes={@ORM\Index(name="id_membre", columns={"id_membre"}), @ORM\Index(name="id_options", columns={"id_options"})})
 * @ORM\Entity
 */
class Answers 
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
     * @var \Options
     *
     * @ORM\ManyToOne(targetEntity="Options")
     * @ORM\JoinColumns({
     * @ORM\JoinColumn(name="id_options", referencedColumnName="id")
     * })
     */
    private $idoptions;
    
    /**
     * @var string
     *
     * @ORM\Column(name="option1", type="string", length=255, nullable=false)
     */
    private $option1;
    
    /**
     * @var string
     *
     * @ORM\Column(name="option2", type="string", length=255, nullable=false)
     */
    private $option2;
    
    /**
     * @var string
     *
     * @ORM\Column(name="option3", type="string", length=255, nullable=false)
     */
    private $option3;
    
    /**
     * @var \Membre
     *
     * @ORM\ManyToOne(targetEntity="Membre")
     * @ORM\JoinColumns({
     * @ORM\JoinColumn(name="id_membre", referencedColumnName="id")
     * })
     */
    private $idmembre;
    
    function getId() {
        return $this->id;
    }

    function getIdoptions() {
        return $this->idoptions;
    }

    function getIdmembre() {
        return $this->idmembre;
    }

    function setId($id) {
        $this->id = $id;
    }

    function setIdoptions($idoptions) {
        $this->idoptions = $idoptions;
    }

    function setIdmembre( $idmembre) {
        $this->idmembre = $idmembre;
    }



    function getOption1() {
        return $this->option1;
    }

    function getOption2() {
        return $this->option2;
    }

    function getOption3() {
        return $this->option3;
    }

    function setOption1($option1) {
        $this->option1 = $option1;
    }

    function setOption2($option2) {
        $this->option2 = $option2;
    }

    function setOption3($option3) {
        $this->option3 = $option3;
    }


    function __construct($option1, $option2, $option3) {
        $this->option1 = $option1;
        $this->option2 = $option2;
        $this->option3 = $option3;
    }


}

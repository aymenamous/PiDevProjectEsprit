<?php

namespace CrowdRiseBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Probleme
 *
 * @ORM\Table(name="survey")
 * @ORM\Entity
 */
class Survey 
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
     * @ORM\Column(name="question", type="string", length=255, nullable=false)
     */
    private $question;

    function __construct($question) {
        $this->question = $question;
    }

    
    function getId() {
        return $this->id;
    }

    function getQuestion() {
        return $this->question;
    }

    function setId($id) {
        $this->id = $id;
    }

    function setQuestion($question) {
        $this->question = $question;
    }

    public function __toString() {
        return $this->question;
    }




}

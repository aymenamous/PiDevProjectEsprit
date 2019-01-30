<?php

namespace CrowdRiseBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Probleme
 *
 * @ORM\Table(name="options" ,indexes={@ORM\Index(name="id_survey", columns={"id_survey"})})
 * @ORM\Entity
 */
class Options 
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
     * @var \Survey
     *
     * @ORM\ManyToOne(targetEntity="Survey")
     * @ORM\JoinColumns({
     * @ORM\JoinColumn(name="id_survey", referencedColumnName="id")
     * })
     */
    private $idsurvey;
    
    function __construct($option1, $option2, $option3) {
        $this->option1 = $option1;
        $this->option2 = $option2;
        $this->option3 = $option3;
    }








    /**
     * Get id
     *
     * @return integer 
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * Set option1
     *
     * @param string $option1
     * @return Options
     */
    public function setOption1($option1)
    {
        $this->option1 = $option1;

        return $this;
    }

    /**
     * Get option1
     *
     * @return string 
     */
    public function getOption1()
    {
        return $this->option1;
    }

    /**
     * Set option2
     *
     * @param string $option2
     * @return Options
     */
    public function setOption2($option2)
    {
        $this->option2 = $option2;

        return $this;
    }

    /**
     * Get option2
     *
     * @return string 
     */
    public function getOption2()
    {
        return $this->option2;
    }

    /**
     * Set option3
     *
     * @param string $option3
     * @return Options
     */
    public function setOption3($option3)
    {
        $this->option3 = $option3;

        return $this;
    }

    /**
     * Get option3
     *
     * @return string 
     */
    public function getOption3()
    {
        return $this->option3;
    }

    /**
     * Set idsurvey
     *
     * @param \CrowdRiseBundle\Entity\Survey $idsurvey
     * @return Options
     */
    public function setIdsurvey(\CrowdRiseBundle\Entity\Survey $idsurvey = null)
    {
        $this->idsurvey = $idsurvey;

        return $this;
    }

    /**
     * Get idsurvey
     *
     * @return \CrowdRiseBundle\Entity\Survey 
     */
    public function getIdsurvey()
    {
        return $this->idsurvey;
    }
}

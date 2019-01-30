<?php

namespace CrowdRiseBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Commentaire
 *
 * @ORM\Table(name="commentaire", indexes={@ORM\Index(name="id_projet", columns={"id_projet", "id_membre"}), @ORM\Index(name="id_projet_2", columns={"id_projet"}), @ORM\Index(name="id_membre", columns={"id_membre"}), @ORM\Index(name="id_probleme", columns={"id_probleme"})})
 * @ORM\Entity
 */
class Commentaire
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
     * @ORM\Column(name="text_commentaire", type="string", length=30, nullable=false)
     */
    private $textCommentaire;

    /**
     * @var \Probleme
     *
     * @ORM\ManyToOne(targetEntity="Probleme")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_probleme", referencedColumnName="id")
     * })
     */
    private $idProbleme;

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


}

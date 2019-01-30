<?php
namespace CrowdRiseBundle\Entity;

use Doctrine\ORM\EntityRepository;


/**
 * Description of MembreRepository
 *
 * @author Yosra
 */
class MembreRepository  extends EntityRepository{

    
    public function findByLetters($string){
        return $this->getEntityManager()->createQuery('SELECT u FROM CrowdRiseBundle:Membre u  
                WHERE u.nom LIKE :string OR u.prenom LIKE :string')
                ->setParameter('string','%'.$string.'%')
                ->getResult();
    }
    
}


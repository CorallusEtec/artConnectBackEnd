package corallus.artConnect.artConnect.mapper;

import java.util.List;

/**
 * Interface base para mapeamentos de Entidade para DTO e vice-versa
 *
 *
 *
 * @author SamuMeneDev
 * @param <E> Tipo da entidade que será convertida
 * @param <D> Tipo da dto que será convertida
 *
 */

public interface BaseMapper <E, D> {
    /**
     * Converte o DTO D para a Entidade do tipo E
     *
     * @param dto DTO a ser convertida
     * @return Entidade convertida
     */
    E toEntity(D dto);

    /**
     * Converte a Entidade E para a DTO do tipo D
     *
     * @param entity Entidade a ser convertida
     * @return DTO convertida
     */
    D toDTO(E entity);

    /**
     * Converte uma Lista de DTO do tipo D em uma Lista de Entidade do tipo E
     *
     * @param dtoList Lista de DTO do tipo D
     * @return Lista da Entidade E convertida
     */

    List<E> toEntityList(List<D> dtoList);

    /**
     *  Converte uma lista de Entidade do tipo E para uma Lista de DTO do tipo D
     *
     * @param entityList Lista de Entidade do tipó E
     * @return Lista de DTO do tipo D convertida
     */

    List<D> toDTOList(List<E> entityList);
}

package ru.lightstar.clinic;

import ru.lightstar.clinic.drug.Aspirin;
import ru.lightstar.clinic.drug.Drug;
import ru.lightstar.clinic.drug.DrugFactory;
import ru.lightstar.clinic.drug.Glucose;
import ru.lightstar.clinic.exception.ServiceException;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Clinic's drug service operating various operations on drug list.
 *
 * @author LightStar
 * @since 0.0.1
 */
public class DrugService {

    /**
     * Clinic manipulated by this service.
     */
    private final Clinic clinic;

    /**
     * Drug factory used to create drugs.
     */
    private final DrugFactory drugFactory;

    /**
     * Constructs <code>DrugService</code> object.
     *
     * @param clinic clinic object.
     */
    public DrugService(final Clinic clinic) {
        super();
        this.clinic = clinic;

        this.drugFactory = new DrugFactory();
        this.drugFactory.addDrug(Aspirin.class);
        this.drugFactory.addDrug(Glucose.class);
    }

    /**
     * Get map of all drugs in clinic with their counts.
     *
     * @return map of all drugs with their counts.
     */
    public synchronized Map<Drug, Integer> getAllDrugs() {
        final Map<Drug, Integer> resultMap = new LinkedHashMap<>();

        for (final Drug drug : this.clinic.getDrugList()) {
            if (resultMap.containsKey(drug)) {
                resultMap.put(drug, resultMap.get(drug) + 1);
            } else {
                resultMap.put(drug, 1);
            }
        }

        return resultMap;
    }

    /**
     * Get all known drug names from drug factory.
     *
     * @return array of all known drug names.
     */
    public String[] getKnownDrugNames() {
        return this.drugFactory.getKnownNames();
    }

    /**
     * Add new drug to clinic.
     *
     * @param name drug's name
     * @return added drug object.
     * @throws ServiceException thrown when drug's name is unknown.
     */
    public synchronized Drug addDrug(final String name) throws ServiceException {
        this.checkDrugName(name);

        final Drug drug = this.drugFactory.create(name);
        this.clinic.getDrugList().add(drug);

        return drug;
    }

    /**
     * Take drug from clinic (efficiently removing it).
     *
     * @param name drug's name to remove.
     * @return taken drug object.
     * @throws ServiceException thrown when there are no such drug in clinic.
     */
    public synchronized Drug takeDrug(final String name) throws ServiceException {
        Drug takenDrug = Drug.NONE;
        final Iterator<Drug> iterator = this.clinic.getDrugList().iterator();

        while (iterator.hasNext()) {
            final Drug drug = iterator.next();
            if (drug.getName().equals(name)) {
                iterator.remove();
                takenDrug = drug;
                break;
            }
        }

        if (takenDrug == Drug.NONE) {
            throw new ServiceException("Drug not found");
        }

        return takenDrug;
    }

    /**
     * Checks if provided drug's name is correct.
     *
     * @param drugName drug's name.
     * @throws ServiceException thrown if drug's name is incorrect.
     */
    private void checkDrugName(final String drugName) throws ServiceException {
        if (!this.drugFactory.isKnownName(drugName)) {
            throw new ServiceException("Unknown drug name");
        }
    }
}

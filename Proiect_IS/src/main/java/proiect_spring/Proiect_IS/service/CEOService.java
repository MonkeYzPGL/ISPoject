package proiect_spring.Proiect_IS.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proiect_spring.Proiect_IS.model.*;
import proiect_spring.Proiect_IS.repository.*;

import java.util.List;
import java.util.Optional;

@Service
public class CEOService {

    @Autowired
    private CEORepository ceoRepository;

    @Autowired
    private AngajatRepository angajatRepository;

    @Autowired
    private TeamLeaderRepository teamLeaderRepository;

    @Autowired
    private ProiectRepository proiectRepository;

    @Autowired
    private CerereProiectRepository cerereProiectRepository;

    @Autowired
    private ProiectService proiectService;

    @Autowired
    private EchipaService echipaService;

    @Autowired
    private CerereMarireRepository cerereMarireRepository;

    @Autowired
    private CerereMarireService cerereMarireService;

    public List<CEO> getAllCEOs() {
        return ceoRepository.findAll();
    }

    public CEO getCEOById(int id) {
        return ceoRepository.findById(id).orElse(null);
    }

    public CEO saveCEO(CEO ceo) {
        return ceoRepository.save(ceo);
    }

    public void deleteCEO(int id) {
        ceoRepository.deleteById(id);
    }

    public void schimbaSalariuAngajat(int ceoId, int angajatId, int salariu) {
        CEO ceo = ceoRepository.findById(ceoId).orElse(null);
        Angajat angajat = angajatRepository.findById(angajatId).orElse(null);

        if (ceo != null && angajat != null) {
            angajat.setSalariu(salariu);
            angajatRepository.save(angajat);
        }
    }

    @Transactional
    public void schimbaSalariuTeamLeader(int ceoId, int teamLeaderId, int salariu) {
        CEO ceo = ceoRepository.findById(ceoId).orElse(null);
        TeamLeader teamLeader = teamLeaderRepository.findById(teamLeaderId).orElse(null);

        if (ceo != null && teamLeader != null) {
            teamLeader.setSalariu(salariu);
            teamLeaderRepository.save(teamLeader);
        }
    }

    public List<Echipa> getEchipe() {
        return echipaService.getAllEchipe();
    }

    public List<Proiect> getProiecte() {
        return proiectService.getAllProjects();
    }

    public void gestioneazaCerereMarire(int ceoID, int angajatId, int salariu) {
        Optional<CEO> optionalCEO = ceoRepository.findById(ceoID);
        if (optionalCEO.isPresent()) {
            CEO ceo = optionalCEO.get();

            //verific daca angajatul exista
            Angajat angajat = angajatRepository.findById(angajatId).orElse(null);
            if (angajat != null) {
                CerereMarire cerereMarireAngajat = cerereMarireRepository.findByAngajatId(angajatId);
                if (cerereMarireAngajat.isCerereAprobata()) {
                    schimbaSalariuAngajat(ceoID, angajatId, salariu);
                    //daca se schimba salariul, atunci se elimina cererea de marire din tabela de cereri.
                    cerereMarireService.stergeCerereMarire(cerereMarireAngajat.getId());
                }else{
                    int salariuVechi = angajat.getSalariu();
                    schimbaSalariuAngajat(ceoID, angajatId, salariuVechi);
                }
            }
        }
    }


    public void schimbaStareCerereMarireAngajat(int ceoId, int angajatId, boolean nouaStare) {
        CEO ceo = ceoRepository.findById(ceoId).orElse(null);
        if (ceo != null) {
            // Verificăm dacă angajatul există
            Angajat angajat = angajatRepository.findById(angajatId).orElse(null);
            if (angajat != null) {
                // Verificăm dacă există cererea de mărire pentru angajat
                CerereMarire cerereMarire = cerereMarireRepository.findByAngajatId(angajatId);
                if (cerereMarire != null) {
                    // Setăm noua stare pentru cererea de mărire
                    cerereMarire.setCerereAprobata(nouaStare);

                    // Salvăm modificările la cererea de mărire
                    cerereMarireRepository.save(cerereMarire);
                }
            }

        }
    }
}
package com.tempotalent.api.tier;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TierService {
  private final TierRepository repository;

  public TierService(TierRepository repository) {
    this.repository = repository;
  }

  public List<Tier> fetch() {
    return repository.findAll();
  }

  public Tier fetchById(Integer id) {
    return repository.findById(id).orElse(null);
  }

  public Tier add(String level, Double priceY, Double priceM, Double price2Y) {
    var tier = new Tier();
    tier.setLevel(level);
    tier.setPrice1Y(priceY);
    tier.setPriceM(priceM);
    tier.setPrice2Y(price2Y);
    return repository.save(tier);
  }

  public Boolean delete(Integer id) {
    try {
      repository.deleteById(id);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}

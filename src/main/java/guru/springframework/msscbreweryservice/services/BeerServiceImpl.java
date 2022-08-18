package guru.springframework.msscbreweryservice.services;

import java.util.UUID;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import guru.springframework.msscbreweryservice.repositories.BeerRepository;
import guru.springframework.msscbreweryservice.web.mappers.BeerMapper;
import guru.springframework.msscbreweryservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {
   

}

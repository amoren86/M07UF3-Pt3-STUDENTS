package cat.institutmarianao.service.impl;

import java.util.List;

import cat.institutmarianao.model.Item;
import cat.institutmarianao.repository.ItemRepository;
import cat.institutmarianao.service.ItemService;
import jakarta.validation.constraints.NotNull;

//TODO Convert it in a service
public class ItemServiceImpl implements ItemService {

	// TODO Inject repository
	private ItemRepository itemRepository;

	@Override
	public Item get(@NotNull Long reference) {
		return itemRepository.get(reference);
	}

	@Override
	public List<Item> getAll() {
		return itemRepository.getAll();
	}

}

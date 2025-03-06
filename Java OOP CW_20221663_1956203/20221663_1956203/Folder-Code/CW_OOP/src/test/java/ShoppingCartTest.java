import org.example.ShoppingCart;
import org.example.User;
import org.junit.jupiter.api.*;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class ShoppingCartTest {


    private ShoppingCart cart;
    private User testUser ;

    @BeforeAll
    public static void setupAll() {
        System.out.println("Iniciando os testes da classe ShoppingCart.");
    }

    @BeforeEach
    public void setup() {
        cart = new ShoppingCart();
        testUser  = new User("testUser ", "pass1234"); 
        cart.addUser (testUser .getUsername(), testUser .getPassword());
    }

    @AfterEach
    public void resetEntities() {
        cart = null;
        testUser  = null;
    }

    @AfterAll
    public static void afterAllTests() {
        System.out.println("Todos os testes da classe foram concluídos.");
    }

    @Test
    @DisplayName("Testa adicionar um item ao carrinho")
    public void testAddToCart() {
        cart.addToCart("1", "Produto A", 10.0, 2, "Categoria A", testUser );
        assertEquals(20.0, cart.getTotal(), "O total deve ser 20.0 após adicionar o Produto A.");
    }

    @Test
    @DisplayName("Testa a aplicação de desconto por categoria")
    public void testCategoryDiscount() {
        cart.addToCart("1", "Produto A", 10.0, 3, "Categoria A", testUser );
        assertEquals(6.0, cart.getDiscount(), "O desconto por categoria deve ser 6.0.");
    }

    @Test
    @DisplayName("Testa a remoção de um item do carrinho")
    public void testRemoveFromCart() {
        cart.addToCart("1", "Produto A", 10.0, 2, "Categoria A", testUser );
        cart.removeFromCart("1", testUser );
        assertTrue(cart.getCartItems().isEmpty());
    }

    @Test
    @DisplayName("Testa a exceção ao remover um item que não existe")
    public void testRemoveNonExistentItem() {
        assertThrows(IllegalArgumentException.class, () -> {
            cart.removeFromCart("999", testUser );
        }, "Deve lançar uma exceção ao tentar remover um item que não existe.");
    }

    @Test
    @DisplayName("Testa o tempo de execução do addToCart")
    public void testAddToCartTimeout() {
        assertTimeout(Duration.ofMillis(100), () -> {
            cart.addToCart("1", "Produto A", 10.0, 2, "Categoria A", testUser );
        });
    }

    @Test
    @DisplayName("Testa o tempo de execução do addToCar (timeoutPreemtively)")
    public void testAddToCartTimeoutPreemptively() {
        assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
            cart.addToCart("1", "Produto A", 10.0, 2, "Categoria A", testUser );
        });
    }

    @Test
    @Disabled()
    @DisplayName("Teste desativado")
    public void testDisabled() {
        System.out.println("Teste não será executado");
    }}

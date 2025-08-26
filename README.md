# APDEX - Teste Unitário com Abordagem TDD

## Descrição

Projeto Java com Maven e JUnit implementando o cálculo e classificação APDEX (Application Performance Index) seguindo a metodologia TDD (Test-Driven Development) conforme padrão ensinado em aula.

## Autor

**Rafael de Souza Pinto - RM 555130**

## Tecnologias

- **Java 17**
- **Maven 3.x**
- **JUnit 5 (Jupiter)**
- **IntelliJ IDEA**

## Estrutura do Projeto

```
src/
├── main/java/org/example/
│   └── Apdex.java          # Implementação com métodos calcularApdex*
└── test/java/org/example/
    ├── ApdexCategoryTest.java  # Testes de categorias (Excelente, Bom, ...)
    └── ApdexTest.java          # Testes de cálculo por valores e bordas
```

## Implementação Final

### Classe Principal: Apdex.java

- **Padrão da aula**: Implementação simplificada com métodos `calcularApdex*`
- **Nome e RM**: Na primeira linha conforme especificação
- **Métodos principais**:
  - `calcularApdex(int s, int to, int ta)` - Cálculo do score APDEX
  - Métodos booleanos para cada classificação
  - `classificarApdex()` - Retorna string da classificação

### Classes de Teste

- **ApdexCategoryTest.java**: Testa limites e classificação textual
- **ApdexTest.java**: Testa valores representativos e casos de borda
- **Annotations**: @BeforeAll para RM 555130, @BeforeEach para instância

## Funcionalidades Implementadas

### Classe Apdex (padrão da aula)

- `calcularApdex(int s, int to, int ta)` - Calcula o score APDEX
- `calcularApdexExcelente(int s, int to, int ta)` - Verifica se é Excelente (≥0.94)
- `calcularApdexBom(int s, int to, int ta)` - Verifica se é Bom (≥0.85)
- `calcularApdexRazoavel(int s, int to, int ta)` - Verifica se é Razoável (≥0.70)
- `calcularApdexRuim(int s, int to, int ta)` - Verifica se é Ruim (≥0.50)
- `calcularApdexInaceitavel(int s, int to, int ta)` - Verifica se é Inaceitável (<0.50)
- `classificarApdex(int s, int to, int ta)` - Retorna classificação textual

### Classificações APDEX

- **EXCELLENT**: ≥ 0.94
- **GOOD**: ≥ 0.85
- **FAIR**: ≥ 0.70
- **POOR**: ≥ 0.50
- **UNACCEPTABLE**: < 0.50

## Testes Implementados

- `ApdexCategoryTest` cobre limites e classificação textual
- `ApdexTest` cobre valores representativos e casos de borda

### Exemplos de Testes por Classificação

- **EXCELLENT**: `calcularApdex(94, 6, 100) -> 0.97`
- **GOOD**: `calcularApdex(90, 0, 100) -> 0.90`
- **FAIR**: `calcularApdex(70, 10, 100) -> 0.75`
- **POOR**: `calcularApdex(50, 20, 100) -> 0.60`
- **UNACCEPTABLE**: `calcularApdex(0, 50, 100) -> 0.25`

### Annotations JUnit

- **@BeforeAll**: Preparação do dataset grande (555130 amostras)
- **@Test**: Todos os métodos de teste unitário

## Como Executar

### Pré-requisitos

- JDK 17
- Maven 3.x
- IntelliJ IDEA (recomendado)

### Executar Testes

```bash
# Via Maven
mvn test

# Via IntelliJ
# Clique direito em ApdexTest.java → Run 'ApdexTest'
# Clique direito em ApdexCategoryTest.java → Run 'ApdexCategoryTest'
```

### Build do Projeto

```bash
mvn clean compile
```

## Repositório GitHub

[Apdex Repository](https://github.com/RafaellSouzaPinto/Apdex.git)

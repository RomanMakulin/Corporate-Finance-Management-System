package com.wayz.CFMS.models.subModels;

public enum BudgetCategories {
    OPERATING_EXPENSES("Операционные расходы"),
    CAPITAL_EXPENDITURES("Капитальные расходы"),
    MARKETING_AND_ADVERTISING("Маркетинг и реклама"),
    RESEARCH_AND_DEVELOPMENT("Исследования и разработки"),
    FINANCIAL_EXPENSES("Финансовые расходы"),
    HUMAN_RESOURCES("Управление персоналом"),
    LOGISTICS_AND_SUPPLY_CHAIN("Логистика и поставки"),
    INFORMATION_TECHNOLOGY("Информационные технологии"),
    CORPORATE_SOCIAL_RESPONSIBILITY("Корпоративная социальная ответственность"),
    PRODUCTION_EXPENSES("Производственные расходы"),
    LEGAL_EXPENSES("Юридические расходы"),
    CUSTOMER_SERVICE("Обслуживание клиентов"),
    BUSINESS_DEVELOPMENT("Бизнес-развитие");

    private final String description;

    BudgetCategories(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

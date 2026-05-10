STANDALONE_CONFIG="standalone.xml"
DEPLOYMENTS_DIR="$WILDFLY_HOME/standalone/deployments/"


# Colors
GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
NC='\033[0m'

# Helpers
print_success() {
  echo -e "${GREEN}$1${NC}"
}

print_error() {
  echo -e "${RED}$1${NC}"
}

print_info() {
  echo -e "${YELLOW}$1${NC}"
}

print_info "Building Maven project..."
cd ..

mvn clean install
if [ $? -ne 0 ]; then
  print_error "Maven build failed"
  exit 1
fi

print_success "Maven build successful"

EAR_FILE=$(find . -name "*.ear" | grep "/target/" | head -n 1) # head means get the first one found (ear)
if [ -z "$EAR_FILE" ]; then # -z if the previous string is empty
  print_error "EAR file not found"
  exit 1
fi

print_success "EAR found: $EAR_FILE"

#Clean

print_info "Cleaning deployment directory..."

rm -f "$DEPLOYMENTS_DIR"/*.ear
rm -f "$DEPLOYMENTS_DIR"/*.failed
rm -f "$DEPLOYMENTS_DIR"/*.deployed
rm -f "$DEPLOYMENTS_DIR"/*.isdeploying
rm -f "$DEPLOYMENTS_DIR"/*.pending
rm -f "$DEPLOYMENTS_DIR"/*.undeployed
rm -f "$DEPLOYMENTS_DIR"/*.dodeploy
rm -rf "$DEPLOYMENTS_DIR"/*.ear.tmp

print_success "Deployment directory cleaned"

# Copy ear file

print_info "Copying EAR to WildFly deployments folder..."

cp "$EAR_FILE" "$DEPLOYMENTS_DIR"
if [ $? -ne 0 ]; then
  print_error "Failed to copy EAR"
  exit 1
fi

print_success "EAR copied successfully"

# Create deploy marked
EAR_NAME=$(basename "$EAR_FILE")
DEPLOY_MARKER="$DEPLOYMENTS_DIR/$EAR_NAME.dodeploy"
print_info "Creating deployment marker..."

touch DEPLOY_MARKER

print_success "Deployment marker created"

print_info "Starting WildFly..."
cd "$WILDFLY_HOME/bin" # $WILDFLY_HOME has set it up into your local variable env machine
./standalone.sh -c "$STANDALONE_CONFIG"